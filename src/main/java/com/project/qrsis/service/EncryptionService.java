package com.project.qrsis.service;

import java.io.IOException;

public interface EncryptionService {

    String encrypt(String value);

    String decrypt(String encrypted);
}
