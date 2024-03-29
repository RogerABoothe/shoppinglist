package com.rlssgroup;

import com.azure.identity.DefaultAzureCredential;
import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import com.azure.security.keyvault.secrets.models.KeyVaultSecret;
import com.azure.security.keyvault.secrets.models.SecretProperties;

public class azureAuth {

    public static String[] authDetails(String sName, String vName) {
        String secretName = sName;
        String keyVaultName = vName; 
        String keyVaultUri = "https://" + keyVaultName + ".vault.azure.net"; 
        String[] vault = new String[2];

        DefaultAzureCredential defaultCredential = new DefaultAzureCredentialBuilder().build();
        SecretClient secretClient = new SecretClientBuilder()
            .vaultUrl(keyVaultUri)
            .credential(defaultCredential)
            .buildClient();
        logging.logEvent("Getting secret from "  + keyVaultName + " for " + secretName);
        KeyVaultSecret retrievedSecret = secretClient.getSecret(secretName);
        SecretProperties secretProperties = secretClient.getSecret(secretName).getProperties();
        logging.logEvent("Getting content type from " + keyVaultName + " for " + secretName);
        String contentType = secretProperties.getContentType();

        vault[0] = contentType.substring(11);
        vault[1] = retrievedSecret.getValue();
        return vault;
    }
}