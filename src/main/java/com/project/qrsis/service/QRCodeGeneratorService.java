package com.project.qrsis.service;

import java.awt.image.BufferedImage;

public interface QRCodeGeneratorService {

    BufferedImage generate(String data);
}
