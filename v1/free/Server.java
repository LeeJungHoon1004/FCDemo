package free;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

class Connection extends Thread {
	private Socket sock;
	private DataInputStream dis;
	private DataOutputStream dos;
	private ArrayList<Instances> i = new ArrayList<Instances>();

	public Connection(Socket sock, DataInputStream dis, DataOutputStream dos) {
		super();
		this.sock = sock;
		this.dis = dis;
		this.dos = dos;
	}

	public void run() {
		try {

			String sign = dis.readUTF();

			if (sign.equals("�߰�")) {
				int index = dis.readInt();
				File f = new File("L://������//���Ͽ���");

				for (int i = 0; i < index; i++) {
					FileInputStream fis = new FileInputStream(f);
					ObjectInputStream ois = new ObjectInputStream(fis);
					Instances ins = (Instances) ois.readObject();
					ois.close();
				}

			} else if (sign.equals("���ε�")) {
				try {
					File f = new File("L://������//���Ͽ���");

					for (int j = 0; j < i.size(); j++) {

						FileOutputStream fos = new FileOutputStream(f);
						ObjectOutputStream oos = new ObjectOutputStream(fos);
						dos.writeInt(i.size());
						oos.writeObject(i);// spb ���� ����.
						oos.close();
					}
				} catch (Exception e1) {
					System.out.println("Ŀ�´�Ƽ ������ ����");
				}
			}
		} catch (Exception e1) {
			System.out.println("�����Խ��� unmarshalling ����");
		}
	}

}

public class Server {
	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(40000);

			while (true) {
				Socket sock = server.accept();
				DataInputStream dis = new DataInputStream(sock.getInputStream());
				DataOutputStream dos = new DataOutputStream(sock.getOutputStream());

				Connection con = new Connection(sock, dis, dos);
			}
		} catch (Exception e) {
			System.out.println("�ʱ⿬�����");
		}
	}
}
