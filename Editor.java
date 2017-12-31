import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

public class Editor implements ActionListener
{
JMenuItem mi,mi1,mi2,mi3,mi4,mi5,mi6,mi66,mi9,mi7,mi8,mi0,i,i1,i2;
JFrame f; 
JScrollPane s;
 final JTextArea textArea = new JTextArea(29, 20);
       JTextArea jta;
JMenuBar j;
JScrollPane sp;
JMenu m,m1,m2,m3,m4;
BorderLayout b;
Runtime r;
JLabel j1,j2;
String fname,result="",result1=" ",fname1;
int ii=0,jj=0;
Editor(){
b= new BorderLayout();

 f=new JFrame("Notepad");
j=new JMenuBar();
m=new JMenu("File");
m1=new JMenu("Edit");
m2=new JMenu("Format");
m3=new JMenu("Compile");
m4=new JMenu("Run");
mi=new JMenuItem("New");
mi1=new JMenuItem("Open");
mi2=new JMenuItem("Save");
mi3=new JMenuItem("Save as");
//mi4=new JMenuItem("Print");
mi5=new JMenuItem("Exit");
//mi6=new JMenuItem("Undo");
//mi66=new JMenuItem("Redo");

mi7=new JMenuItem("Cut");
mi8=new JMenuItem("Copy");
mi9=new JMenuItem("Paste");
i=new JMenuItem("Font");
i1=new JMenuItem("compile");
i2=new JMenuItem("run");


//*******************************JLabels

 j1 = new JLabel();
 j2 = new JLabel();
 j2.setText("Console:");      

//**********************************TextAreas


textArea.setLineWrap(true);
textArea.setWrapStyleWord(true);

textArea.setEditable(true);
  //sp=new JScrollPane();
// sp.setViewportView(textArea);
//f.getContentPane().add(sp);
  // s=new JScrollPane(textArea);
  // s.setPreferredSize(new Dimension(1380, 100));
  //   s.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
  //   textArea.setCaretPosition(textArea.getDocument().getLength());

                   
 // f.getContentPane().add(s , SwingConstants.CENTER);


jta=new JTextArea(9,34);
jta.setWrapStyleWord(true);
jta.setLineWrap(true);

jta.setEditable(false);




  //*******  making menu functional
  mi.addActionListener(this);
 mi1.addActionListener(this);
 mi2.addActionListener(this);
 mi3.addActionListener(this);
 i1.addActionListener(this);
 i2.addActionListener(this);
 //mi6.addActionListener(this);
 //mi66.addActionListener(this);
 mi7.addActionListener(this);
 mi8.addActionListener(this);
 mi9.addActionListener(this);
 mi5.addActionListener(this);

//***************   redo action

UndoManager manager=new UndoManager();
 textArea.getDocument().addUndoableEditListener(manager);

f.add(j1);


f.getContentPane().add(textArea,b.PAGE_START);
f.getContentPane().add(jta,b.PAGE_END);

//f.getContentPane().add(sp);

m.add(mi);   //m.addSeparator();
m.add(mi1);   //m.addSeparator();
m.add(mi2);
m.add(mi3);
m.addSeparator();
//m.add(mi4);
m.add(mi5);
m1.add(UndoManagerHelper.getUndoAction(manager));
m1.add(UndoManagerHelper.getRedoAction(manager));
m1.addSeparator();
m1.add(mi7);
m1.add(mi8); 
m1.add(mi9);
m2.add(i);
m3.add(i1);
m4.add(i2);

j.add(m);
j.add(m1);
j.add(m2);
j.add(m3);
j.add(m4);

//mi.addActionListener(this);


f.addWindowListener( new WindowAdapter() {
public void windowClosing(WindowEvent e)
{
	System.exit(0);
			}
		} );
 
r=Runtime.getRuntime();

f.setResizable(false);
f.setJMenuBar(j);
f.add(j1);
f.add(j2);
f.setSize(1300,700);
f.setLocation(0,0);
f.setVisible(true);
 //f.setLayout(new BorderLayout());
f.setLayout(b);

}





// ****************functionality*************

   public void actionPerformed(ActionEvent e)
{

         if(e.getSource()==mi)
         {
               String data=textArea.getText().trim();//read contents of text area into 'data'
               System.out.println(data);
  if(data.equals("")){
                      
               textArea.setText(" ");
                jta.setText(" ");
                   fname=null;

               }
           else{
              String[] options = {"Save", "Don't Save", "Cancel"};
            int x = JOptionPane.showOptionDialog(null, "Do you want to save the file?",
                "Select",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        //System.out.println(x);

             if(x==0) 
                 {  saveOption();
                    }
             else if(x==1)
              {                                 
               textArea.setText(" ");
                jta.setText(" ");
                   fname=null;
                     }
}
}
         else  if(e.getSource()==mi1)
      {
         openFile();


   System.out.println("open");
      }
          else   if(e.getSource()==mi3)
          saveFile();
         
           else  if(e.getSource()==mi2)
           {
              saveOption();
         }

            else  if(e.getSource()==mi5)
          System.exit(0);
 

            else  if(e.getSource()==mi7)
          //System.out.println("cut");
             textArea.cut();
            else  if(e.getSource()==mi8)
          {
                textArea.copy();
            
              System.out.println("copy");
          }

            else  if(e.getSource()==mi9)
            textArea.paste();
           
            else  if(e.getSource()==i1)
            {
                     try
                     {
                            if(fname==null)    
                            saveFile();
                
                //*******code to compile the program

                Process error= r.exec("C:\\Program Files\\Java\\jdk1.8.0_111\\bin\\javac.exe -d . " +fname);
                BufferedReader err= new BufferedReader(new InputStreamReader(error.getErrorStream()));  
                while(true)
                 {
                    //    System.out.println("hii");
                   String temp=err.readLine();
                    if(temp!=null)
                      {
                       result+=temp;
                      result+="\n";
                         ii=1;
                                        
              }
                     else
                   {
                      break; 
                      } 
                      jta.setText(result+"\n");     
                   
                     
                    
                   }   //ends while loop
                        err.close();
                     
                        if(ii==0)
                           {
                              result="Compilation Successful       "+fname+"\n\n";
                               jta.setText(result);     
                   
                    }
                     }
                       catch(Exception e3)
                     {
                           System.out.println(e3);}
 


}
            //************************running the file
           else  if(e.getSource()==i2)
            {
                     try
                     {
                           //String arr[]=fname.split("\\\");
                           // fname= arr[arr.length-1];

                  //String  fname= fname.substring(fname.indexOf("\") + 1, fname.indexOf("."));
                  System.out.println(fname);
                 Process p= r.exec("C:\\Program Files\\Java\\jdk1.8.0_111\\bin\\java " +fname1);
                BufferedReader output= new BufferedReader(new InputStreamReader(p.getInputStream()));  
               BufferedReader err= new BufferedReader(new InputStreamReader(p.getErrorStream()));  
              

             //********for reading the output
                    result=" ";
                       while(true)
                 {
                    //    System.out.println("hii");
                   String temp=output.readLine();
                    if(temp!=null)
                      {
                       result+=temp;
                      result+="\n";
                         
                                        
              }
                     else
                   {
                      break; 
                      } }    //while ends

                 //****************** for reading the errors
                          result1=" ";
                       while(true)
                 {
                    //    System.out.println("hii");
                   String temp=err.readLine();
                    if(temp!=null)
                      {
                       result1+=temp;
                      result1+="\n";
                         
                                        
              }
                     else
                   {
                      break; 
                      } 
                     
                    
                   }  
                      output.close();
                     err.close();
                    
                  //ends while loop
                    jta.setText(result+"\n\n" + result1);     
                          
                       
                }
                   catch(Exception e4)
                     {
                           System.out.println(e4);}
 
          
      }   //elseif ends

}     //function ends

void saveFile(){
    try{
        JFileChooser j= new JFileChooser();
        j.setCurrentDirectory(new File(System.getProperty("user.home")));
        int i=j.showSaveDialog(null);  

         if(i==j.APPROVE_OPTION)
         {
                 System.out.println("Saving....");
                 fname= j.getSelectedFile()+".java"; 
                  fname1=fname;
                  int l=fname1.lastIndexOf("\\");
                  fname1= fname1.substring(l+1, fname1.length()-5);
      
                 FileWriter fw= new FileWriter(fname);
                 String s=textArea.getText();
                 PrintWriter p =new PrintWriter(fw);
                 p.println(s);
                 p.flush();

              }

}

catch(Exception e2)
{
   e2.printStackTrace();
}
}


void openFile(){  
JFileChooser fc=new JFileChooser(); 
fc.setCurrentDirectory(new File(System.getProperty("user.home")));
 
int i=fc.showOpenDialog(null);  
          if(i==fc.APPROVE_OPTION)
         {
    File f=fc.getSelectedFile();
     String s= f.getPath();
     fname=fname1=s;
         int l=fname.lastIndexOf("\\");
                  fname1= fname1.substring(l+1, fname1.length()-5);
               
     display(s);
}
}

void display(String path)
{
        try{
         String s1="",s2="";
      BufferedReader br =new BufferedReader(new FileReader(path));
       while((s1=br.readLine())!=null)
{        s2=s2+s1+"\n";
}
       textArea.setText(s2);
          br.close();
         }

catch(Exception e1)
{
e1.printStackTrace();
}



}

//   for new save file option
   void  saveOption()
{
     if(fname==null)
          saveFile();
            else
        {        try{
                 fname1=fname;
                  int l=fname1.lastIndexOf("\\");
                  fname1= fname1.substring(l+1, fname1.length()-5);
      
                 FileWriter fw= new FileWriter(fname);
                 String s=textArea.getText();
                 PrintWriter p =new PrintWriter(fw);
                 p.println(s);
                 p.flush();
                       }
                   catch(Exception e9)
                     {
                           System.out.println(e9);}
        
      

             }
}



public static void main(String... s)
{
  new Editor();
}}


//*************8undo -Redo

class UndoManagerHelper {

  public static Action getUndoAction(UndoManager manager, String label) {
    return new UndoAction(manager, label);
  }

  public static Action getUndoAction(UndoManager manager) {
    return new UndoAction(manager, "Undo");
  }

  public static Action getRedoAction(UndoManager manager, String label) {
    return new RedoAction(manager, label);
  }

  public static Action getRedoAction(UndoManager manager) {
    return new RedoAction(manager, "Redo");
  }

  private abstract static class UndoRedoAction extends AbstractAction {
    UndoManager undoManager;// = new UndoManager();

    String errorMessage = "Cannot undo";

    String errorTitle = "Undo Problem";

    protected UndoRedoAction(UndoManager manager, String name) {
      super(name);
      undoManager = manager;
    }

    public void setErrorMessage(String newValue) {
      errorMessage = newValue;
    }

    public void setErrorTitle(String newValue) {
      errorTitle = newValue;
    }

    protected void showMessage(Object source) {
      if (source instanceof Component) {
        JOptionPane.showMessageDialog((Component) source, errorMessage,
            errorTitle, JOptionPane.WARNING_MESSAGE);
      } else {
        System.err.println(errorMessage);
      }
    }
  }

  public static class UndoAction extends UndoRedoAction {
    public UndoAction(UndoManager manager, String name) {
      super(manager, name);
      setErrorMessage("Cannot undo");
      setErrorTitle("Undo Problem");
    }

    public void actionPerformed(ActionEvent actionEvent) {
      try {
        undoManager.undo();
      } catch (CannotUndoException cannotUndoException) {
        showMessage(actionEvent.getSource());
      }
    }
  }

  public static class RedoAction extends UndoRedoAction {
    String errorMessage = "Cannot redo";

    String errorTitle = "Redo Problem";

    public RedoAction(UndoManager manager, String name) {
      super(manager, name);
      setErrorMessage("Cannot redo");
      setErrorTitle("Redo Problem");
    }

    public void actionPerformed(ActionEvent actionEvent) {
      try {
        undoManager.redo();
      } catch (CannotRedoException cannotRedoException) {
        showMessage(actionEvent.getSource());
      }
    }
  }

}
