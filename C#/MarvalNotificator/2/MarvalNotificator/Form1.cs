using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.IO;

namespace MarvalNotificator
{
    public partial class Form1 : Form
    {
        public string AppStartDir;
        public string newFile;
        public string oldFile;
        public string toBeReplacedWithNewLineSymbol;
        public string startSearch;
        public string finistSearch;
        public string toBeRemoved1;
        public string toBeRemoved2;
        public string SoundToPlay;
        public string[] paramsStrings;


        public Form1()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            FindTasks();
        }




        private void Form1_Load(object sender, EventArgs e)
        {
            AppStartDir = Path.GetDirectoryName(Environment.CommandLine.Replace('"', ' '));

            //из файла "newFile" будем вырезать "задачи" и искать их в файле "oldFile". Если не найдем, значит задача - новая
            newFile = AppStartDir + "\\new.txt";
            oldFile = AppStartDir + "\\old.txt";
            SoundToPlay = AppStartDir + "\\SoundToPlay.wav";

            paramsStrings = File.ReadAllLines(AppStartDir + "\\params.ini", Encoding.UTF8);

            textBox1.Text = paramsStrings[0];  //username
            textBox2.Text = paramsStrings[1];   //psw
            toBeReplacedWithNewLineSymbol = paramsStrings[2];   //тут вставлять "новую строку". Чтоб проще искать построчно было, а то все задачи изначально в одной строке находятся
            startSearch = paramsStrings[3];   //Начало "задачи"
            finistSearch = paramsStrings[4];   //Конец "задачи"
            toBeRemoved1 = paramsStrings[5];    //Внутри подстроки с "задачей" находится мусор. Удалить
            toBeRemoved2 = paramsStrings[6];    //Внутри подстроки с "задачей" находится мусор. Удалить
            textBox3.Text = paramsStrings[7];       //url
        }




        private void FindTasks()
        {
//            MessageBox.Show("event");
            if (File.Exists(newFile))
            {

                //Старый "new" файл превращается в "old"                
                File.Delete(oldFile);
                File.Copy(newFile, oldFile);
                File.Delete(newFile);
                File.WriteAllText(newFile, webBrowser1.DocumentText);

                //Вставляем символы новой строки, чтоб в этих строках искать задачи марвала
                string str = File.ReadAllText(newFile);
                str = str.Replace(toBeReplacedWithNewLineSymbol, System.Environment.NewLine);
                File.WriteAllText(newFile, str);

                //Ищем задачи марвала в файле "new"
                //Каждую найденную задачу из файла "new" потом поищем в "old". Если не найдем - значит она новая
//                textBox4.Clear();
                foreach (string strNew in File.ReadAllLines(newFile, Encoding.UTF8))
                {
                    int start = strNew.IndexOf(startSearch);
                    if (start != -1)
                    {
                        int finish = strNew.IndexOf(finistSearch);
                        if (finish != -1)
                        {
                            //Ищем задачу в строке нового файле по шаблонам
                            string MarvalTask = strNew.Substring(start + startSearch.Length, finish - start - finistSearch.Length - 4);

                            /*RegExp   http://stackoverflow.com/questions/5876107/c-sharp-substring-indexof*/


                            //Ищем задачу из нового файла в старом файле                            
                            bool taskFound = false;
                            foreach (string strOld in File.ReadAllLines(oldFile, Encoding.UTF8))
                            {                                
                                if (strOld.IndexOf(MarvalTask) != -1)
                                {
                                    //Есть совпадение. Задача не новая                                    
                                    taskFound = true;
                                    break;
                                }
                            }

                            if (taskFound == false)
                            {                                
                                //Задача в старом файле не найдена. Сигнализируем
                                MarvalTask = MarvalTask.Replace(toBeRemoved1, "   -   ");   //Удалить мусор
                                MarvalTask = MarvalTask.Replace(toBeRemoved2, "");          //Удалить мусор
                                textBox4.AppendText(Environment.NewLine + MarvalTask + Environment.NewLine);
                                textBox4.AppendText("===================================================================");

                                new System.Media.SoundPlayer(SoundToPlay).PlaySync();
                            }
                        }
                    }                    
                }
            }

            else

            {
                File.WriteAllText(AppStartDir + "\\new.txt", webBrowser1.DocumentText);
            }




            webBrowser1.Navigate(new Uri(textBox3.Text));
        }




        private void timer1_Tick(object sender, EventArgs e)
        {
            FindTasks();
        }
    }
}
