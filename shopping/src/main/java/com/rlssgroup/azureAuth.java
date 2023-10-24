package com.rlssgroup;

import java.io.IOException;
import com.azure.identity.DefaultAzureCredential;
import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import com.azure.security.keyvault.secrets.models.KeyVaultSecret;
import com.azure.security.keyvault.secrets.models.SecretProperties;

public class AzureAuth {

    public static String[] authDetails(String sName, String vName) throws IOException {
        String secretName = sName;
        String keyVaultName = vName; 
        String keyVaultUri = "https://" + keyVaultName + ".vault.azure.net"; 
        String[] vault = new String[2];

        DefaultAzureCredential defaultCredential = new DefaultAzureCredentialBuilder().build();
        SecretClient secretClient = new SecretClientBuilder()
            .vaultUrl(keyVaultUri)
            .credential(defaultCredential)
            .buildClient();
        Logging.logEvent("Getting secret from "  + keyVaultName + " for " + secretName);
        KeyVaultSecret retrievedSecret = secretClient.getSecret(secretName);
        SecretProperties secretProperties = secretClient.getSecret(secretName).getProperties();
        Logging.logEvent("Getting content type from " + keyVaultName + " for " + secretName);
        String contentType = secretProperties.getContentType();

        vault[0] = contentType.substring(11);
        vault[1] = retrievedSecret.getValue();
        return vault;
    }
}