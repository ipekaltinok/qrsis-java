package com.project.qrsis.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

@Service
public class QRCodeGeneratorServiceImpl implements QRCodeGeneratorService {

    public BufferedImage generate(String data) {

        try {

            Map<EncodeHintType, Object> hintMap = new HashMap<>();
            hintMap.put(EncodeHintType.MARGIN, new Integer(1));
            BitMatrix matrix = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, 570, 570, hintMap);

            return MatrixToImageWriter.toBufferedImage(matrix);

        } catch (Exception e) {
            return null;
        }
    }
}
