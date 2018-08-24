package com.sino.newasia.neworder.Controller;

import org.springframework.stereotype.Component;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;


@Component(value="testme")
public class Test {

        //char[] 数组转为byte[] 数组
        public static byte[] getBytes(char[] chars) {
            Charset cs = Charset.forName("UTF-8");
            CharBuffer cb = CharBuffer.allocate(chars.length);
            cb.put(chars);
            cb.flip();
            ByteBuffer bb = cs.encode(cb);
            return bb.array();
        }

        //byte[] 数组转为数组 char[]

        public static char[] getChars(byte[] bytes) {
            Charset cs = Charset.forName("UTF-8");
            ByteBuffer bb = ByteBuffer.allocate(bytes.length);
            bb.put(bytes);
            bb.flip();
            CharBuffer cb = cs.decode(bb);
            return cb.array();
        }

        //char 转 byte[] 数组

        public static byte[] charToByte(char c) {
            byte[] b = new byte[2];
            b[0] = (byte) ((c & 0xFF00) >> 8);
            b[1] = (byte) (c & 0xFF);
            return b;
        }

        //byte[] 数组转 char

        public static char byteToChar(byte[] b) {
            char c = (char) (((b[0] & 0xFF) << 8) | (b[1] & 0xFF));
            return c;
        }

}
