sap.ui.define([
	"sap/ui/base/ManagedObject"
], function(
	ManagedObject
) {
	"use strict";

	return ManagedObject.extend("CICDMTA.CICDMTA.services.sfservice", {
        constructor: function () {
        },

        // Function to make an HTTP GET request
        getData: function (url) {
            return new Promise(function (resolve, reject) {
                jQuery.ajax({
                    url: url,
                    type: "GET",
                    dataType: "json",
                    success: function (data) {
                        resolve(data);
                    },
                    error: function (error) {
                        reject(error);
                    }
                });
            });
        },

        // Function to make an HTTP POST request
        postData: function (url, payload) {
            return new Promise(function (resolve, reject) {
                jQuery.ajax({
                    url: url,
                    type: "POST",
                    contentType: "application/json",
                    data: JSON.stringify(payload),
                    success: function (data) {
                        resolve(data);
                    },
                    error: function (error) {
                        reject(error);
                    }
                });
            });
        }
    });
});