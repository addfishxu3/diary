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
	private String[] area= {"臺北市","新北市","桃園市","臺中市","臺南市","高雄市","基隆市",
			"新竹市","新竹縣","苗栗縣","彰化縣","南投縣","雲林縣","嘉義市",
			"嘉義縣","屏東縣","宜蘭縣","花蓮縣","臺東縣","澎湖縣","金門縣","連江縣"};
	public diarytest() {        
		super("日程表");         
		setUpUIComponent();         
		setUpEventListener();        
		setVisible(true);     
		}     
	private void setUpUIComponent() {  
		
		setSize(800, 600);         
		// 選單列         
		JMenuBar menuBar = new JMenuBar();         
		// 設置「檔案」選單         
		JMenu fileMenu = new JMenu("檔案"); 
		//快速鍵設置         
		menuOpen = new JMenuItem("開啟舊檔");                
		         
		menuSave = new JMenuItem("儲存檔案");         
		
		menuSaveAs = new JMenuItem("另存新檔");         
		menuClose = new JMenuItem("關閉");         
		   
		fileMenu.add(menuOpen);
		fileMenu.addSeparator(); // 分隔線         
		fileMenu.add(menuSave);         
		fileMenu.add(menuSaveAs);               
		fileMenu.addSeparator(); // 分隔線        
		fileMenu.add(menuClose); 
		
		//選擇地區天氣//	Wx(天氣現象)、MaxT(最高溫度)、MinT(最低溫度)、CI(舒適度)、PoP(降雨機率).
		//"臺北市","新北市","桃園市","臺中市","臺南市","高雄市","基隆市","新竹市","新竹縣","苗栗縣",
		//"彰化縣","南投縣","雲林縣","嘉義市","嘉義縣","屏東縣","宜蘭縣","花蓮縣","臺東縣","澎湖縣",
		//"金門縣","連江縣"
		
		JMenu areaMenu = new JMenu("天氣");         
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
		
		// 文字編輯區域         
		textArea = new JTextArea();         
		textArea.setFont(new Font("新細明", Font.PLAIN, 16));         
		textArea.setLineWrap(true);         
		JScrollPane panel = new JScrollPane(textArea,           
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,           
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);         
		Container contentPane = getContentPane();         
		contentPane.add(panel, BorderLayout.CENTER);
		
		//檔案狀態
		stateBar = new JLabel("檔案狀態：未修改");         
		stateBar.setHorizontalAlignment(SwingConstants.RIGHT);          
		stateBar.setBorder(BorderFactory.createEtchedBorder());         
		contentPane.add(stateBar, BorderLayout.SOUTH); 
		
		
	}
	private void setUpEventListener() {         
		// 按下視窗關閉鈕事件處理         
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {                      
				closeFile();                 
				}             
			}         
		);
		//開啟舊檔
		menuOpen.addActionListener(new ActionListener() {                 
			 public void actionPerformed(ActionEvent e) {                     
				 openFile();                 
				 }             
			 }         
		); 
		//儲存檔案 
		menuSave.addActionListener(new ActionListener() {                 
			public void actionPerformed(ActionEvent e) {                     
				saveFile();                 
				}             
			}         
		); 
		 //另存新檔         
		menuSaveAs.addActionListener(new ActionListener() {                 
			public void actionPerformed(ActionEvent e) {                     
				saveFileAs();                 
				}             
			}         
		); 
		//關閉檔案
		menuClose.addActionListener(new ActionListener() {                 
			public void actionPerformed(ActionEvent e) {                     
				closeFile();                 
				}             
			}         
		);
		
		//天氣資訊
		menuTaipei.addActionListener(new ActionListener() {                 
			public void actionPerformed(ActionEvent e) {                     
				// 顯示對話方塊  
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
				// 顯示對話方塊  
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
				// 顯示對話方塊  
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
				// 顯示對話方塊  
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
				// 顯示對話方塊  
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
				// 顯示對話方塊  
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
				// 顯示對話方塊  
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
				// 顯示對話方塊  
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
				// 顯示對話方塊  
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
				// 顯示對話方塊  
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
				// 顯示對話方塊  
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
				// 顯示對話方塊  
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
				// 顯示對話方塊  
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
				// 顯示對話方塊  
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
				// 顯示對話方塊  
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
				// 顯示對話方塊  
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
				// 顯示對話方塊  
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
				// 顯示對話方塊  
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
				// 顯示對話方塊  
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
				// 顯示對話方塊  
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
				// 顯示對話方塊  
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
				// 顯示對話方塊  
				try {
					getjson(21);
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
			}	 
		}        
		);
		
		//編輯事件
		textArea.addKeyListener(new KeyAdapter() {                 
			public void keyTyped(KeyEvent e) {                     
				processTextArea();                 
				}             
			}         
		);
		
	}     
	 private void openFile() {
		 if(isCurrentFileSaved()) { // 文件是否為儲存狀態         
			 open(); // 開啟舊檔     
			 }else {         
				 // 顯示對話方塊         
				 int option = JOptionPane.showConfirmDialog(null, "檔案已修改，是否儲存？","儲存檔案？", JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE, null);         
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
		 if(stateBar.getText().equals("檔案狀態：已修改")) {         
			 return false;     
		 }else {         
			 return true;     
				 
		 } 
	} 
	 private void open() {    
		 
		 fileChooser=new JFileChooser();
		 int option = fileChooser.showDialog(null, null);     
		 // 使用者按下確認鍵 
		 if(option == JFileChooser.APPROVE_OPTION) {         
			 try {             // 開啟選取的檔案             
			 BufferedReader buf = new BufferedReader(new InputStreamReader(new FileInputStream(fileChooser.getSelectedFile()),"UTF-8"));
			 // 設定文件標題 清除前一次文件             
			 setTitle(fileChooser.getSelectedFile().toString());          
			 textArea.setText("");             // 設定狀態列  
			 stateBar.setText("檔案狀態：未修改");             // 取得系統相依的換行字元             
			 String lineSeparator = System.getProperty("line.separator");             
			 // 讀取檔案並附加至文字編輯區             
			 String text;             
			 while ( ( text = buf.readLine()) != null) {textArea.append(text); textArea.append(lineSeparator);             
			 }             
			 buf.close();         
			 }catch(IOException e) {             
				 JOptionPane.showMessageDialog(null, e.toString(),"開啟檔案失敗", JOptionPane.ERROR_MESSAGE);     
				 }         
			 } 
		 }
	 
	 private void saveFile() {
		 // 從標題列取得檔案名稱 
		 File file = new File(getTitle());
		 // 若指定的檔案不存在     
		 if(!file.exists()) {         // 執行另存新檔
			 saveFileAs();     
			 }else {         
				try {     
					FileOutputStream files = new FileOutputStream(getTitle());// 開啟指定的檔案             
					BufferedWriter buf =new BufferedWriter(new OutputStreamWriter(files, "UTF-8")); 
					//將文字編輯區的文字寫入檔案             
					buf.write(textArea.getText());             
					buf.close();             // 設定狀態列為未修改  
					stateBar.setText("檔案狀態：未修改");         
				}catch(IOException e) {             
					JOptionPane.showMessageDialog(null, e.toString(),"寫入檔案失敗",JOptionPane.ERROR_MESSAGE);
			 }
		 }
	 }         
	 
	 private void saveFileAs() {
		 // 顯示檔案對話方塊     
		 JFileChooser fileChooser=new JFileChooser();
		 int option = fileChooser.showDialog(null, null);     
		 // 如果確認選取檔案     
		 if(option == JFileChooser.APPROVE_OPTION) {         
			 // 取得選擇的檔案         
			 File file = fileChooser.getSelectedFile(); 
			 //在標題列上設定檔案名稱         
			 setTitle(file.toString());         
			 try{             
				 // 建立檔案 
				 file.createNewFile(); 
				 // 進行檔案儲存                 
				 saveFile();         
			 }catch(IOException e) {         
				 JOptionPane.showMessageDialog(null, e.toString(),"無法建立新檔",JOptionPane.ERROR_MESSAGE);         }     }    
			 }
  
	 private void closeFile() {
		 if(isCurrentFileSaved()) {         
			 // 釋放視窗資源，而後關閉程式         
			 dispose(); 
			 System.exit(0);
		 }else {         
			 int option = JOptionPane.showConfirmDialog(null, "檔案已修改，是否儲存？","儲存檔案？"
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
		 stateBar.setText("檔案狀態：已修改"); 
	 }
	
	 private void getjson(int i) throws UnsupportedEncodingException {
		 String url ="https://opendata.cwb.gov.tw/api/v1/rest/datastore/F-C0032-001?Authorization="
					+token+"&format=JSON&locationName="+URLEncoder.encode(area[i],"utf-8");
		 JOptionPane.showOptionDialog(null,loadJson(url),                         
					"今明36小時天氣資訊",
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
		        	  jsonf.append("降雨機率："+objn13.getString("parameterName")+"%\n");
		        	  jsonf.append("氣溫："+objn23.getString("parameterName")+"~");
		        	  jsonf.append(objn33.getString("parameterName")+"度\n");
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