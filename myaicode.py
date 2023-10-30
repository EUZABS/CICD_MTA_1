from tensorflow.keras.layers import AveragePooling2D,AvgPool2D,ReLU
import pandas as pd
import numpy as np
import tensorflow as tf
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Conv2D, MaxPooling2D, Flatten, Dense
from tensorflow.keras.preprocessing.image import img_to_array, load_img
from tensorflow.keras.preprocessing.image import ImageDataGenerator
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import LabelEncoder

# Load the CSV file containing filenames and target values
csv_file_path = '/content/train.csv'
data = pd.read_csv(csv_file_path)

# Split the dataframe into train, validation, and test sets
train_data, temp_data = train_test_split(data, test_size=0.2, random_state=42)
val_data, test_data = train_test_split(temp_data, test_size=0.5, random_state=42)

def image_generator(dataframe, data_dir, batch_size=32):
    while True:
        batch_df = dataframe.sample(batch_size)
        batch_images = []
        batch_labels = []
        for index, row in batch_df.iterrows():
          try:
            img = load_img(data_dir + row['filename'], target_size=(224, 224))
            img = img_to_array(img)
            img = datagen.random_transform(img)  # Apply random transformations for data augmentation
            label = row['extent']
            batch_images.append(img)
            batch_labels.append(label)
            batch_images = np.array(batch_images)
            batch_labels = np.array(batch_labels)
          except:
            pass;
        yield batch_images, batch_labels
        

def mobilnet_block(inputs, filters, strides):
    x = Conv2D(filters = filters, kernel_size = 1, strides = 1, kernel_initializer='he_normal')(inputs)
    x = BatchNormalization()(x)
    x = ReLU()(x)
    x = DepthwiseConv2D(kernel_size = 3, strides = strides, padding = 'same', kernel_initializer='he_normal')(x)
    x = BatchNormalization()(x)
    x = ReLU()(x)
    x = Conv2D(filters = filters, kernel_size = 1, strides = 1, kernel_initializer='he_normal')(x)
    x = BatchNormalization()(x)
    if strides == 1:
      x = Add()([x, inputs])
    return x

    
# Define data directory
data_dir = 'train/'

# Use the image generator function for train, val, and test data
batch_size = 32
train_generator = image_generator(train_data, data_dir, batch_size=batch_size)
val_generator = image_generator(val_data, data_dir, batch_size=batch_size)
test_generator = image_generator(test_data, data_dir, batch_size=batch_size)

# Get the number of batches for each dataset
num_batches_train = len(train_data) // batch_size
num_batches_val = len(val_data) // batch_size
num_batches_test = len(test_data) // batch_size

input = Input(shape=(224,224,3))
x = Conv2D(32, kernel_size=(3, 3), padding='same', strides=2)(input)

x = mobilnet_block(x, filters = 32, strides = 1)
x = mobilnet_block(x, filters = 64, strides = 2)
x = mobilnet_block(x, filters = 64, strides = 1)
x = mobilnet_block(x, filters = 128, strides = 2)
x = mobilnet_block(x, filters = 128, strides = 1)
x = mobilnet_block(x, filters = 256, strides = 2)
x = mobilnet_block(x, filters = 256, strides = 1)
x = mobilnet_block(x, filters = 512, strides = 2)
x = mobilnet_block(x, filters = 512, strides = 1)

x = AveragePooling2D(pool_size=(7,7))(x)
output_tensor = Dense(units=1, activation='linear')(x)

model = tf.keras.Model(inputs=[input], outputs=output_tensor)

model.compile(optimizer=tf.keras.optimizers.Adam(learning_rate=0.001), loss='mean_squared_error', metrics=[tf.keras.metrics.RootMeanSquaredError()])

# model.summary()
model.fit_generator(
    train_generator,
    steps_per_epoch=num_batches_train,  # Specify the number of steps per epoch
    epochs=10,
    validation_data=val_generator,
    validation_steps=num_batches_val,  # Specify the number of validation steps
)
