sap.ui.define(["sap/ui/test/Opa5"], function (Opa5) {
    "use strict";

    return Opa5.extend("CICDMTA.CICDMTA.test.integration.arrangements.Startup", {
        iStartMyApp: function () {
            this.iStartMyUIComponent({
                componentConfig: {
                    name: "CICDMTA.CICDMTA",
                    async: true,
                    manifest: true,
                },
            });
        },
    });
});
