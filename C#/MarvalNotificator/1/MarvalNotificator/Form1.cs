using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Threading.Tasks;
using System.Windows.Forms;

using System.IO;
using System.Net;
using System.Text;

namespace MarvalNotificator
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            /*
            WebRequest request = WebRequest.Create("http://www.contoso.com/PostAccepter.aspx ");            
            request.Method = "POST";            
            string postData = "This is a test that posts this string to a Web server.";
            byte[] byteArray = Encoding.UTF8.GetBytes(postData);
            request.ContentType = "application/x-www-form-urlencoded";            
            request.ContentLength = byteArray.Length;            
            Stream dataStream = request.GetRequestStream();            
            dataStream.Write(byteArray, 0, byteArray.Length);            
            dataStream.Close();
            
                        
            WebResponse response = request.GetResponse();            
            textBox1.Text = ((HttpWebResponse)response).StatusDescription;            
            dataStream = response.GetResponseStream();            
            StreamReader reader = new StreamReader(dataStream);            
            string responseFromServer = reader.ReadToEnd();            
            textBox1.Text = responseFromServer;            
            reader.Close();
            dataStream.Close();
            response.Close();
            */

            //==================================================================================================================

            //            WebRequest request = WebRequest.Create("http://www.contoso.com/default.html");
            //            WebRequest request = WebRequest.Create("http://dnepropetrovsk.volia.com/rus/ HTTP/1.1 Host: dnepropetrovsk.volia.com User - Agent: Mozilla / 5.0(Windows NT 6.1; rv: 40.0) Gecko / 20100101 Firefox / 40.0 Accept: text / html,application / xhtml + xml,application / xml; q = 0.9,*/*;q=0.8 Accept-Language: ru-RU,ru;q=0.8,en-US;q=0.5,en;q=0.3 Accept-Encoding: gzip, deflate Cookie: __utma=56072236.1821986222.1410291467.1425569757.1425821932.15; __utmz=56072236.1425821932.15.3.utmcsr=dnepropetrovsk.volia.com|utmccn=(referral)|utmcmd=referral|utmcct=/rus/; saveduserdata=%7B%22variant%22%3A%22active%22%2C%22firstname%22%3A%22%D0%9A%D0%BE%D0%BD%D1%81%D1%82%D0%B0%D0%BD%D1%82%D0%B8%D0%BD%22%2C%22lastname%22%3A%22%D0%93%D0%BB%D0%B0%D0%B4%D0%BA%D0%B8%D0%B9%22%2C%22phone%22%3A%220684078940%22%2C%22sms%22%3A%221%22%2C%22email%22%3A%22konstantin.gladky%40gmail.com%22%2C%22account%22%3A%22700607144%22%2C%22apartment%22%3A%22116%22%2C%22city%22%3A%22%D0%94%D0%BD%D0%B5%D0%BF%D1%80%D0%BE%D0%BF%D0%B5%D1%82%D1%80%D0%BE%D0%B2%D1%81%D0%BA%22%2C%22street%22%3A%22%D0%A2%D0%BE%D0%BF%D0%BE%D0%BB%D1%8C%201%20%D0%B6%5C%5C%D0%BC%22%2C%22house%22%3A%2218%20%D0%9A2%22%7D; checkcookie=true; _ga=GA1.2.1821986222.1410291467; _ga=GA1.3.1821986222.1410291467; __utma=238421589.1821986222.1410291467.1433701555.1433701555.1; __utmz=238421589.1433701555.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); _gat=1; _dc_gtm_UA-994962-1=1; _ym_visorc_9408661=w Connection: keep-alive");

            WebRequest request = WebRequest.Create("http://dnepropetrovsk.volia.com/rus/ Cookie: utma=56072236.1821986222.1410291467.1425569757.1425821932.15; __utmz=56072236.1425821932.15.3.utmcsr=dnepropetrovsk.volia.com|utmccn=(referral)|utmcmd=referral|utmcct=/rus/; saveduserdata=%7B%22variant%22%3A%22active%22%2C%22firstname%22%3A%22%D0%9A%D0%BE%D0%BD%D1%81%D1%82%D0%B0%D0%BD%D1%82%D0%B8%D0%BD%22%2C%22lastname%22%3A%22%D0%93%D0%BB%D0%B0%D0%B4%D0%BA%D0%B8%D0%B9%22%2C%22phone%22%3A%220684078940%22%2C%22sms%22%3A%221%22%2C%22email%22%3A%22konstantin.gladky%40gmail.com%22%2C%22account%22%3A%22700607144%22%2C%22apartment%22%3A%22116%22%2C%22city%22%3A%22%D0%94%D0%BD%D0%B5%D0%BF%D1%80%D0%BE%D0%BF%D0%B5%D1%82%D1%80%D0%BE%D0%B2%D1%81%D0%BA%22%2C%22street%22%3A%22%D0%A2%D0%BE%D0%BF%D0%BE%D0%BB%D1%8C%201%20%D0%B6%5C%5C%D0%BC%22%2C%22house%22%3A%2218%20%D0%9A2%22%7D; checkcookie=true; _ga=GA1.2.1821986222.1410291467; _ga=GA1.3.1821986222.1410291467; __utma=238421589.1821986222.1410291467.1433701555.1433701555.1; __utmz=238421589.1433701555.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); _gat=1; _dc_gtm_UA-994962-1=1; _ym_visorc_9408661=w");            
            request.Credentials = CredentialCache.DefaultCredentials;   // If required by the server, set the credentials.            
            WebResponse response = request.GetResponse();
                        
            for (int i = 0; i < response.Headers.Count; ++i) {
                textBox2.Text = textBox2.Text + "  " + response.Headers.Keys[i] + "  " + response.Headers[i];
            }

        Stream dataStream = response.GetResponseStream();
            StreamReader reader = new StreamReader(dataStream);            
            string responseFromServer = reader.ReadToEnd();
            
            textBox1.Text = responseFromServer;

            reader.Close();
            response.Close();

        }
    }
}
