# import pandas as pd
# import numpy as np
# import tensorflow as tf
# from tensorflow.keras.models import Sequential
# from tensorflow.keras.layers import Conv2D, MaxPooling2D, Flatten, Dense
# from tensorflow.keras.preprocessing.image import img_to_array, load_img
# from tensorflow.keras.preprocessing.image import ImageDataGenerator
# from sklearn.model_selection import train_test_split
# from sklearn.preprocessing import LabelEncoder
# from tensorflow.keras.preprocessing.image import ImageDataGenerator

# # Image generator
# datagen = ImageDataGenerator(rescale=1./255)  # Normalize pixel values to [0, 1]

# def image_generator(dataframe, data_dir, batch_size=32):
#     while True:
#         batch_df = dataframe.sample(batch_size)
#         batch_images = []
#         batch_labels = []
#         for index, row in batch_df.iterrows():
#           try:
#             img = load_img(data_dir + row['filename'], target_size=(150, 150))
#             img = img_to_array(img)
#             img = datagen.random_transform(img)  # Apply random transformations for data augmentation
#             label = row['extent']
#             batch_images.append(img)
#             batch_labels.append(label)
#           except:
#             pass
#         yield np.array(batch_images), np.array(batch_labels)

# # Configurations
# hyperparameters_list = [
#     {"conv_filters": 32, "kernel_size": (2, 2), "pool_size": (2, 2), "dense_units": 512},
#     {"conv_filters": 64, "kernel_size": (2, 2), "pool_size": (2, 2), "dense_units": 512},
#     {"conv_filters": 32, "kernel_size": (3, 3), "pool_size": (2, 2), "dense_units": 512},
#     {"conv_filters": 64, "kernel_size": (3, 3), "pool_size": (2, 2), "dense_units": 512},

#     # Add more configurations as needed
# ]

# csv_file_path = '/content/train.csv'
# data = pd.read_csv(csv_file_path)

# train_df, temp_data = train_test_split(df, test_size=0.2, random_state=42)
# val_df, test_df = train_test_split(temp_data, test_size=0.5, random_state=42)
# # Define data directory
# data_dir = 'train/'

# # Use the image generator function
# batch_size = 64
# train_generator = image_generator(train_df, data_dir, batch_size=batch_size)
# val_generator = image_generator(val_df, data_dir, batch_size=batch_size)

# # Encode labels to numerical values
# label_encoder = LabelEncoder()
# encoded_labels = label_encoder.fit_transform(data['extent'])

# for idx, hyperparameters in enumerate(hyperparameters_list):
#   print(f'Model {idx}')

#   # Build the CNN model
#   model = Sequential()
#   model.add(Conv2D(hyperparameters['conv_filters'], hyperparameters['kernel_size'], activation='relu', input_shape=(150, 150, 3)))
#   model.add(MaxPooling2D(hyperparameters['pool_size']))
#   model.add(Conv2D(hyperparameters['conv_filters'], hyperparameters['kernel_size'], activation='relu'))
#   model.add(MaxPooling2D(hyperparameters['pool_size']))
#   model.add(Flatten())
#   model.add(Dense(hyperparameters['dense_units'], activation='relu'))
#   model.add(Dense(1, activation='linear'))  # Assuming you're predicting a continuous value

#   # Compile the model
#   model.compile(optimizer='adam', loss='mean_squared_error', metrics=['mse'])  # Use appropriate loss function for your task

#   # Get the number of batches per epoch
#   num_batches_per_epoch = len(data) // batch_size

#   # Train the model using the generator
#   model.fit_generator(
#       generator=train_generator,
#       steps_per_epoch=num_batches_per_epoch,
#       epochs=10,
#       validation_data=val_generator
#   )

from diffusers import AutoPipelineForText2Image
import torch

pipeline_text2image = AutoPipelineForText2Image.from_pretrained(
    "stabilityai/stable-diffusion-xl-base-1.0", torch_dtype=torch.float16, variant="fp16", use_safetensors=True
).to("cuda")

prompt = "Astronaut in a jungle, cold color palette, muted colors, detailed, 8k"
image = pipeline(prompt=prompt).images[0]