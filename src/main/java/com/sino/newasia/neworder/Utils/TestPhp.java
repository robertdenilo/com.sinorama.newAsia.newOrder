package com.sino.newasia.neworder.Utils;

import org.mindrot.jbcrypt.BCrypt;

public class TestPhp {
    public static void main(String args[]) throws Exception {
        //String localpath = new TestPhp().getClass().getClassLoader().getResource("").getPath();
//        FileOutputStream phpfile = new FileOutputStream("pwdverify.php",false);
//        phpfile.write("if (password_verify($argv[1], $argv[2])){echo \"success\"; ".getBytes());
//        phpfile.write("\r\n".getBytes());
//        phpfile.write("}else{echo \"fail\";}".getBytes());
//        phpfile.flush();
//        phpfile.close();


//        System.out.println("123");
//        ProcessBuilder pb = new ProcessBuilder(
//                "C:\\XAMPP\\php\\php.exe",
//                "C:\\pwdverify.php",
//                "Sinorama123",
//                "$2y$10$HYkHJhPtEdX5GV27ZXrQ1.NRoJIRn8ShOXo/9pHYIr7IKGF2O1zq2"
//                );
//        Process p = pb.start();
//        InputStream in = p.getInputStream();
//        InputStreamReader reader = new InputStreamReader(in);
//        char[] buff = new char[1024];
//        reader.read(buff);
//        System.out.println(buff);//打印出!!!!!

        String password  = "Sinorama123";
        String hashcode = BCrypt.hashpw(password,BCrypt.gensalt());
        System.out.println(hashcode);

        if(BCrypt.checkpw("Sinorama123","$2a$10$HYkHJhPtEdX5GV27ZXrQ1.NRoJIRn8ShOXo/9pHYIr7IKGF2O1zq2")){
            System.out.println("Matched");
        }else{
            System.out.println("Not Matchec");
        }

    }
}
