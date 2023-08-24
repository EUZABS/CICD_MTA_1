/* global QUnit */

QUnit.config.autostart = false;

sap.ui.getCore().attachInit(function () {
    "use strict";

    sap.ui.require(["CICDMTA/CICDMTA/test/integration/AllJourneys"], function () {
        QUnit.start();
    });
});
