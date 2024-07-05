window.onload = function() {
    // Customize Swagger UI
    const ui = SwaggerUIBundle({
        url: "/v3/api-docs",
        dom_id: '#swagger-ui',
        deepLinking: true,
        presets: [
            SwaggerUIBundle.presets.apis,
            SwaggerUIStandalonePreset
        ],
        plugins: [
            SwaggerUIBundle.plugins.DownloadUrl
        ],
        layout: "StandaloneLayout",
        oauth2RedirectUrl: window.location.origin + '/swagger-ui/oauth2-redirect.html'
    });

    ui.initOAuth({
        clientId: "projet42-api",
        clientSecret: "UjttrpYWQb78I0wVtlxTc3bHJnDM0zqc",
        realm: "projet42-realm",
        appName: "Swagger UI",
        scopeSeparator: " ",
        additionalQueryStringParams: {},
        useBasicAuthenticationWithAccessCodeGrant: true
    });

    window.ui = ui;
};
