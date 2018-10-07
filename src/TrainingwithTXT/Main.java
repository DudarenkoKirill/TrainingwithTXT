package TrainingwithTXT;

import java.io.*;
import java.util.Scanner;

public class Main {
        public static void main(String[] args) {
            boolean flag=true;
            Scanner scanner = new Scanner(System.in);
            do {
                System.out.println("Введите полный путь к катологу из которого будут выбраны файлы : ");
                File dir = new File(scanner.nextLine());
                System.out.println("Введите полный путь к катологу в который будут помещены файлы : ");
                File newdir = new File(scanner.nextLine());
                try {
                    if(!dir.isDirectory()||!newdir.isDirectory())
                        throw new MyException();
                    Find(dir,newdir);
                }catch (MyException e){
                    e.Info();
                }catch (Exception e){
                    System.out.println("Ошибочка");
                }finally {
                    flag=false;
                    System.out.println("Желаете продолжить?(YES/NO) : ");
                    if(scanner.next().equals("YES"))
                        flag=true;
                }
            }while(flag);
        }
        private static void Find(File dir,File newdir) throws NullPointerException,IOException{
            if(dir.isDirectory())
            {
                for(File item : dir.listFiles()){
                    if(item.isDirectory()){
                        Find(new File((dir.getPath()+"\\"+item.getName())),newdir);
                    }
                    else{
                        System.out.println(item.getName() + "\t file");
                        Main obj=new Main();
                        obj.CreateClone(item,newdir);
                    }

                }
            }
        }

        private void CreateClone(File item,File newdir) throws IOException{
            File newItem=new File(newdir+item.getName());
            InputStream inputStream=new FileInputStream(item);
            byte[]buffer=new byte[inputStream.available()];
            inputStream.read(buffer,0,buffer.length);
            OutputStream outputStream=new FileOutputStream(newItem);
            outputStream.write(buffer,0,buffer.length);
            }
        }


