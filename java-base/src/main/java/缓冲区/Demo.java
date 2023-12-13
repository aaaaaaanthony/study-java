package 缓冲区;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * ByteBuffer
 */
public class Demo {

    public static void main(String[] args) {
        try (FileChannel channel = new FileInputStream("data.txt").getChannel()) {

            // 开辟内存空间
            ByteBuffer buffer = ByteBuffer.allocate(10);

            while (true) {
                // 把读到的内容,写入buffer
                int read = channel.read(buffer);
                System.out.println("读取到的字节:"+read);

                // 判断有没有读完
                if (read == -1) {
                    break;
                }

                // 切换到读模式
                buffer.flip();

                // 是否还有剩余未读数据
                while (buffer.hasRemaining()) {
                    byte b = buffer.get();
                    System.out.println("实际字节:"+(char) b);
                }

                // 切换到写模式
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

}
