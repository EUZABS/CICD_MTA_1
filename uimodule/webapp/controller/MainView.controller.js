// sap.ui.define(
//     ["./BaseController",
//     "sap/ui/table/Table",
//     "sap/ui/commons/Label",
//     "sap/ui/table/Column"],
//     /**
//      * @param {typeof sap.ui.core.mvc.Controller} Controller
//      */
//     function (Controller) {
//         "use strict";

//         return Controller.extend("CICDMTA.CICDMTA.controller.MainView", {
//             onInit() {
//                 const url = 'https://cicdmta-service.cfapps.jp10.hana.ondemand.com/v1/users/allUsers'
//                 // const destinationUrl = 'sfService/users/allUsers'
//                 jQuery.ajax({
//                     url: url,
//                     type: "GET",
//                     dataType: "json",
//                         success: function (data) {
//                             const oModel = new sap.ui.model.json.JSONModel(data);
                            
//                             // Create instance of table control
//                             const oTable = new sap.ui.table.Table({
//                                     title : "User Data",
//                                     visibleRowCount : 6,
//                                     firstVisibleRow : 0,
//                                     alternateRowColors : true,
//                                     width: 100%
//                             });
//                             // First column firstName
//                             oTable.addColumn(new sap.ui.table.Column({
//                                     label : new sap.ui.commons.Label({
//                                         text : "First Name"
//                                     }),
//                                     template : new sap.ui.commons.TextView().bindProperty("text", "firstName"),
//                                     width : "100px"
//                             }));
//                             // Second column lastname
//                             oTable.addColumn(new sap.ui.table.Column({
//                                     label : new sap.ui.commons.Label({
//                                         text : "Last Name"
//                                     }),
//                                     template : new sap.ui.commons.TextView().bindProperty("text", "lastName"),
//                                     width : "100px"

//                             }));
//                             // Third column id
//                             oTable.addColumn(new sap.ui.table.Column({
//                                     label : new sap.ui.commons.Label({
//                                         text : "ID"
//                                     }),
//                                     template : new sap.ui.commons.TextView().bindProperty("text", "personIdExternal"),
//                                     width : "100px"
//                             }));
//                             // Bind model to table control
//                             oTable.setModel(oModel);
//                            oTable.bindRows("/d/results");
//                             oTable.placeAt("content");
//                             console.log(data)
//                         },
//                         error: function (error) {
//                             console.log(error);
//                         }
//                 });
//             }
//         });
// });
