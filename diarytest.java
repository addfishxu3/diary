package diary;

import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent; 
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.awt.event.WindowAdapter;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import javax.swing.JFrame; 
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.JFileChooser;
import javax.swing.*; 
public class diarytest extends JFrame {   
	private JMenuItem menuOpen;     
	private JMenuItem menuSave;     
	private JMenuItem menuSaveAs;     
	private JMenuItem menuClose; 
	private JFileChooser fileChooser;
	private JMenuItem menuTaipei,menuXinbei,menuTaoyuan,menuTaichung,menuTainan,menuKaohsiung,menuKeelung;
	private JMenuItem menuHsinchu1,menuHsinchu2,menuMiaoli,menuChanghua,menuNantou,menuYunlin,menuChiayi1;
	private JMenuItem menuChiayi2,menuPingtung,menuYilan,menuHualien,menuTaitung,menuPenghu,menuKinmen,menuLienchiang;
	private JLabel stateBar; 
	private JTextArea textArea;
	private String token = "CWB-CA507B14-5C33-419A-96E4-A4D6B22C2E95";
	private String[] area= {"�O�_��","�s�_��","��饫","�O����","�O�n��","������","�򶩥�",
			"�s�˥�","�s�˿�","�]�߿�","���ƿ�","�n�뿤","���L��","�Ÿq��",
			"�Ÿq��","�̪F��","�y����","�Ὤ��","�O�F��","���","������","�s����"};
	public diarytest() {        
		super("��{��");         
		setUpUIComponent();         
		setUpEventListener();        
		setVisible(true);     
		}     
	private void setUpUIComponent() {  
		
		setSize(800, 600);         
		// ���C         
		JMenuBar menuBar = new JMenuBar();         
		// �]�m�u�ɮסv���         
		JMenu fileMenu = new JMenu("�ɮ�"); 
		//�ֳt��]�m         
		menuOpen = new JMenuItem("�}������");                
		         
		menuSave = new JMenuItem("�x�s�ɮ�");         
		
		menuSaveAs = new JMenuItem("�t�s�s��");         
		menuClose = new JMenuItem("����");         
		   
		fileMenu.add(menuOpen);
		fileMenu.addSeparator(); // ���j�u         
		fileMenu.add(menuSave);         
		fileMenu.add(menuSaveAs);               
		fileMenu.addSeparator(); // ���j�u        
		fileMenu.add(menuClose); 
		
		//��ܦa�ϤѮ�//	Wx(�Ѯ�{�H)�BMaxT(�̰��ū�)�BMinT(�̧C�ū�)�BCI(�ξA��)�BPoP(���B���v).
		//"�O�_��","�s�_��","��饫","�O����","�O�n��","������","�򶩥�","�s�˥�","�s�˿�","�]�߿�",
		//"���ƿ�","�n�뿤","���L��","�Ÿq��","�Ÿq��","�̪F��","�y����","�Ὤ��","�O�F��","���",
		//"������","�s����"
		
		JMenu areaMenu = new JMenu("�Ѯ�");         
		menuTaipei = new JMenuItem(area[0]);
		menuXinbei = new JMenuItem(area[1]);
		menuTaoyuan = new JMenuItem(area[2]);
		menuTaichung = new JMenuItem(area[3]);
		menuTainan = new JMenuItem(area[4]);
		menuKaohsiung = new JMenuItem(area[5]);
		menuKeelung = new JMenuItem(area[6]);
		menuHsinchu1 = new JMenuItem(area[7]);
		menuHsinchu2 = new JMenuItem(area[8]);
		menuMiaoli = new JMenuItem(area[9]);
		menuChanghua = new JMenuItem(area[10]);
		menuNantou = new JMenuItem(area[11]);
		menuYunlin = new JMenuItem(area[12]);
		menuChiayi1 = new JMenuItem(area[13]);
		menuChiayi2 = new JMenuItem(area[14]);
		menuPingtung = new JMenuItem(area[15]);
		menuYilan = new JMenuItem(area[16]);
		menuHualien = new JMenuItem(area[17]);
		menuTaitung = new JMenuItem(area[18]);
		menuPenghu = new JMenuItem(area[19]);
		menuKinmen = new JMenuItem(area[20]);
		menuLienchiang = new JMenuItem(area[21]);
		areaMenu.add(menuTaipei);areaMenu.add(menuXinbei);areaMenu.add(menuTaoyuan);areaMenu.add(menuTaichung);
		areaMenu.add(menuTainan);areaMenu.add(menuKaohsiung);areaMenu.add(menuKeelung);areaMenu.add(menuHsinchu1);
		areaMenu.add(menuHsinchu2);areaMenu.add(menuMiaoli);areaMenu.add(menuChanghua);areaMenu.add(menuNantou);
		areaMenu.add(menuYunlin);areaMenu.add(menuChiayi1);areaMenu.add(menuChiayi2);areaMenu.add(menuPingtung);
		areaMenu.add(menuYilan);areaMenu.add(menuHualien);areaMenu.add(menuTaitung);areaMenu.add(menuPenghu);
		areaMenu.add(menuKinmen);areaMenu.add(menuLienchiang);
		
		menuBar.add(fileMenu);
		menuBar.add(areaMenu);
		setJMenuBar(menuBar);
		
		// ��r�s��ϰ�         
		textArea = new JTextArea();         
		textArea.setFont(new Font("�s�ө�", Font.PLAIN, 16));         
		textArea.setLineWrap(true);         
		JScrollPane panel = new JScrollPane(textArea,           
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,           
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);         
		Container contentPane = getContentPane();         
		contentPane.add(panel, BorderLayout.CENTER);
		
		//�ɮת��A
		stateBar = new JLabel("�ɮת��A�G���ק�");         
		stateBar.setHorizontalAlignment(SwingConstants.RIGHT);          
		stateBar.setBorder(BorderFactory.createEtchedBorder());         
		contentPane.add(stateBar, BorderLayout.SOUTH); 
		
		
	}
	private void setUpEventListener() {         
		// ���U���������s�ƥ�B�z         
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {                      
				closeFile();                 
				}             
			}         
		);
		//�}������
		menuOpen.addActionListener(new ActionListener() {                 
			 public void actionPerformed(ActionEvent e) {                     
				 openFile();                 
				 }             
			 }         
		); 
		//�x�s�ɮ� 
		menuSave.addActionListener(new ActionListener() {                 
			public void actionPerformed(ActionEvent e) {                     
				saveFile();                 
				}             
			}         
		); 
		 //�t�s�s��         
		menuSaveAs.addActionListener(new ActionListener() {                 
			public void actionPerformed(ActionEvent e) {                     
				saveFileAs();                 
				}             
			}         
		); 
		//�����ɮ�
		menuClose.addActionListener(new ActionListener() {                 
			public void actionPerformed(ActionEvent e) {                     
				closeFile();                 
				}             
			}         
		);
		
		//�Ѯ��T
		menuTaipei.addActionListener(new ActionListener() {                 
			public void actionPerformed(ActionEvent e) {                     
				// ��ܹ�ܤ��  
				try {
					getjson(0);
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
			}	 
		}        
		);
		menuXinbei.addActionListener(new ActionListener() {                 
			public void actionPerformed(ActionEvent e) {                     
				// ��ܹ�ܤ��  
				try {
					getjson(1);
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
			}	 
		}        
		);
		menuTaoyuan.addActionListener(new ActionListener() {                 
			public void actionPerformed(ActionEvent e) {                     
				// ��ܹ�ܤ��  
				try {
					getjson(2);
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
			}	 
		}        
		);
		menuTaichung.addActionListener(new ActionListener() {                 
			public void actionPerformed(ActionEvent e) {                     
				// ��ܹ�ܤ��  
				try {
					getjson(3);
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
			}	 
		}        
		);
		menuTainan.addActionListener(new ActionListener() {                 
			public void actionPerformed(ActionEvent e) {                     
				// ��ܹ�ܤ��  
				try {
					getjson(4);
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
			}	 
		}        
		);
		menuKaohsiung.addActionListener(new ActionListener() {                 
			public void actionPerformed(ActionEvent e) {                     
				// ��ܹ�ܤ��  
				try {
					getjson(5);
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
			}	 
		}        
		);
		menuKeelung.addActionListener(new ActionListener() {                 
			public void actionPerformed(ActionEvent e) {                     
				// ��ܹ�ܤ��  
				try {
					getjson(6);
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
			}	 
		}        
		);
		menuHsinchu1.addActionListener(new ActionListener() {                 
			public void actionPerformed(ActionEvent e) {                     
				// ��ܹ�ܤ��  
				try {
					getjson(7);
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
			}	 
		}        
		);
		menuHsinchu2.addActionListener(new ActionListener() {                 
			public void actionPerformed(ActionEvent e) {                     
				// ��ܹ�ܤ��  
				try {
					getjson(8);
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
			}	 
		}        
		);
		menuMiaoli.addActionListener(new ActionListener() {                 
			public void actionPerformed(ActionEvent e) {                     
				// ��ܹ�ܤ��  
				try {
					getjson(9);
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
			}	 
		}        
		);
		menuChanghua.addActionListener(new ActionListener() {                 
			public void actionPerformed(ActionEvent e) {                     
				// ��ܹ�ܤ��  
				try {
					getjson(10);
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
			}	 
		}        
		);
		menuNantou.addActionListener(new ActionListener() {                 
			public void actionPerformed(ActionEvent e) {                     
				// ��ܹ�ܤ��  
				try {
					getjson(11);
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
			}	 
		}        
		);
		menuYunlin.addActionListener(new ActionListener() {                 
			public void actionPerformed(ActionEvent e) {                     
				// ��ܹ�ܤ��  
				try {
					getjson(12);
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
			}	 
		}        
		);
		menuChiayi1.addActionListener(new ActionListener() {                 
			public void actionPerformed(ActionEvent e) {                     
				// ��ܹ�ܤ��  
				try {
					getjson(13);
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
			}	 
		}        
		);
		menuChiayi2.addActionListener(new ActionListener() {                 
			public void actionPerformed(ActionEvent e) {                     
				// ��ܹ�ܤ��  
				try {
					getjson(14);
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
			}	 
		}        
		);
		menuPingtung.addActionListener(new ActionListener() {                 
			public void actionPerformed(ActionEvent e) {                     
				// ��ܹ�ܤ��  
				try {
					getjson(15);
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
			}	 
		}        
		);
		menuYilan.addActionListener(new ActionListener() {                 
			public void actionPerformed(ActionEvent e) {                     
				// ��ܹ�ܤ��  
				try {
					getjson(16);
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
			}	 
		}        
		);
		menuHualien.addActionListener(new ActionListener() {                 
			public void actionPerformed(ActionEvent e) {                     
				// ��ܹ�ܤ��  
				try {
					getjson(17);
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
			}	 
		}        
		);
		menuTaitung.addActionListener(new ActionListener() {                 
			public void actionPerformed(ActionEvent e) {                     
				// ��ܹ�ܤ��  
				try {
					getjson(18);
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
			}	 
		}        
		);
		menuPenghu.addActionListener(new ActionListener() {                 
			public void actionPerformed(ActionEvent e) {                     
				// ��ܹ�ܤ��  
				try {
					getjson(19);
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
			}	 
		}        
		);
		menuKinmen.addActionListener(new ActionListener() {                 
			public void actionPerformed(ActionEvent e) {                     
				// ��ܹ�ܤ��  
				try {
					getjson(20);
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
			}	 
		}        
		);
		menuLienchiang.addActionListener(new ActionListener() {                 
			public void actionPerformed(ActionEvent e) {                     
				// ��ܹ�ܤ��  
				try {
					getjson(21);
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
			}	 
		}        
		);
		
		//�s��ƥ�
		textArea.addKeyListener(new KeyAdapter() {                 
			public void keyTyped(KeyEvent e) {                     
				processTextArea();                 
				}             
			}         
		);
		
	}     
	 private void openFile() {
		 if(isCurrentFileSaved()) { // ���O�_���x�s���A         
			 open(); // �}������     
			 }else {         
				 // ��ܹ�ܤ��         
				 int option = JOptionPane.showConfirmDialog(null, "�ɮפw�ק�A�O�_�x�s�H","�x�s�ɮסH", JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE, null);         
				 switch(option) {                           
				 case JOptionPane.YES_OPTION:                 
					 saveFile();                   
					 break;                       
				 case JOptionPane.NO_OPTION:                 
					 open();                 
					 break;
					 }     
				 } 
			 }
	 private boolean isCurrentFileSaved() {     
		 if(stateBar.getText().equals("�ɮת��A�G�w�ק�")) {         
			 return false;     
		 }else {         
			 return true;     
				 
		 } 
	} 
	 private void open() {    
		 
		 fileChooser=new JFileChooser();
		 int option = fileChooser.showDialog(null, null);     
		 // �ϥΪ̫��U�T�{�� 
		 if(option == JFileChooser.APPROVE_OPTION) {         
			 try {             // �}�ҿ�����ɮ�             
			 BufferedReader buf = new BufferedReader(new InputStreamReader(new FileInputStream(fileChooser.getSelectedFile()),"UTF-8"));
			 // �]�w�����D �M���e�@�����             
			 setTitle(fileChooser.getSelectedFile().toString());          
			 textArea.setText("");             // �]�w���A�C  
			 stateBar.setText("�ɮת��A�G���ק�");             // ���o�t�ά̪ۨ�����r��             
			 String lineSeparator = System.getProperty("line.separator");             
			 // Ū���ɮרê��[�ܤ�r�s���             
			 String text;             
			 while ( ( text = buf.readLine()) != null) {textArea.append(text); textArea.append(lineSeparator);             
			 }             
			 buf.close();         
			 }catch(IOException e) {             
				 JOptionPane.showMessageDialog(null, e.toString(),"�}���ɮץ���", JOptionPane.ERROR_MESSAGE);     
				 }         
			 } 
		 }
	 
	 private void saveFile() {
		 // �q���D�C���o�ɮצW�� 
		 File file = new File(getTitle());
		 // �Y���w���ɮפ��s�b     
		 if(!file.exists()) {         // ����t�s�s��
			 saveFileAs();     
			 }else {         
				try {     
					FileOutputStream files = new FileOutputStream(getTitle());// �}�ҫ��w���ɮ�             
					BufferedWriter buf =new BufferedWriter(new OutputStreamWriter(files, "UTF-8")); 
					//�N��r�s��Ϫ���r�g�J�ɮ�             
					buf.write(textArea.getText());             
					buf.close();             // �]�w���A�C�����ק�  
					stateBar.setText("�ɮת��A�G���ק�");         
				}catch(IOException e) {             
					JOptionPane.showMessageDialog(null, e.toString(),"�g�J�ɮץ���",JOptionPane.ERROR_MESSAGE);
			 }
		 }
	 }         
	 
	 private void saveFileAs() {
		 // ����ɮ׹�ܤ��     
		 JFileChooser fileChooser=new JFileChooser();
		 int option = fileChooser.showDialog(null, null);     
		 // �p�G�T�{����ɮ�     
		 if(option == JFileChooser.APPROVE_OPTION) {         
			 // ���o��ܪ��ɮ�         
			 File file = fileChooser.getSelectedFile(); 
			 //�b���D�C�W�]�w�ɮצW��         
			 setTitle(file.toString());         
			 try{             
				 // �إ��ɮ� 
				 file.createNewFile(); 
				 // �i���ɮ��x�s                 
				 saveFile();         
			 }catch(IOException e) {         
				 JOptionPane.showMessageDialog(null, e.toString(),"�L�k�إ߷s��",JOptionPane.ERROR_MESSAGE);         }     }    
			 }
  
	 private void closeFile() {
		 if(isCurrentFileSaved()) {         
			 // ��������귽�A�ӫ������{��         
			 dispose(); 
			 System.exit(0);
		 }else {         
			 int option = JOptionPane.showConfirmDialog(null, "�ɮפw�ק�A�O�_�x�s�H","�x�s�ɮסH"
					 ,JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null);         
			 switch(option) {             
			 case JOptionPane.YES_OPTION:                 
				 saveFile();                 
				 break;             
			 case JOptionPane.NO_OPTION:                
				 dispose();
				 System.exit(0);
			 }     
		 } 
	}
	 
	 private void processTextArea() {
		 stateBar.setText("�ɮת��A�G�w�ק�"); 
	 }
	
	 private void getjson(int i) throws UnsupportedEncodingException {
		 String url ="https://opendata.cwb.gov.tw/api/v1/rest/datastore/F-C0032-001?Authorization="
					+token+"&format=JSON&locationName="+URLEncoder.encode(area[i],"utf-8");
		 JOptionPane.showOptionDialog(null,loadJson(url),                         
					"����36�p�ɤѮ��T",
					JOptionPane.DEFAULT_OPTION,                         
					JOptionPane.INFORMATION_MESSAGE,                         
					null, null, null);                 
			}            

	 private static String loadJson (String url) { 
		    StringBuilder json = new StringBuilder();
		    StringBuilder jsonf = new StringBuilder(); 
		    try { 
		    	URL urlObject = new URL(url);
		    	HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
		        connection.setRequestMethod("GET");
		        connection.setDoOutput(true);
		        connection.connect();
		      BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream())); 
		      String inputLine = null; 
		      while ( (inputLine = in.readLine()) != null) { 
		        json.append(inputLine); 
		      } 
		      JSONObject obj = new JSONObject(json.toString());
		      JSONObject objr= obj.getJSONObject("records");
		      JSONArray objl= objr.getJSONArray("location");
		      JSONObject obj0= objl.getJSONObject(0);
		      JSONArray objw= obj0.getJSONArray("weatherElement");
		      
		    	  JSONObject objn1= objw.getJSONObject(1);
		    	  JSONObject objn2= objw.getJSONObject(2);
		    	  JSONObject objn3= objw.getJSONObject(4);
		    	  JSONArray objt= objn1.getJSONArray("time");
		    	  JSONArray objt2= objn2.getJSONArray("time");
		    	  JSONArray objt3= objn3.getJSONArray("time");
		    	  for(int i=0;i<objt.length();i++) {
		        	  JSONObject objn12= objt.getJSONObject(i);
		        	  JSONObject objn22= objt2.getJSONObject(i);
		        	  JSONObject objn32= objt3.getJSONObject(i);
		        	  
		        	  jsonf.append(objn12.getString("startTime")+"-");
		        	  jsonf.append(objn12.getString("endTime")+"\n");
		        	  JSONObject objn13= objn12.getJSONObject("parameter");
		        	  JSONObject objn23= objn22.getJSONObject("parameter");
		        	  JSONObject objn33= objn32.getJSONObject("parameter");
		        	  jsonf.append("���B���v�G"+objn13.getString("parameterName")+"%\n");
		        	  jsonf.append("��šG"+objn23.getString("parameterName")+"~");
		        	  jsonf.append(objn33.getString("parameterName")+"��\n");
		          }

		      in.close(); 
		    } catch (MalformedURLException e) { 
		      e.printStackTrace(); 
		    } catch (IOException e) { 
		      e.printStackTrace(); 
		    } 
		    return jsonf.toString(); 
		  }
	 
	 public static void main(String[] args) {         
		new diarytest();     
	} 
		                   
}