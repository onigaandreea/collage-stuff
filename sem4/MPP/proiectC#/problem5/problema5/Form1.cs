using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using model;
using service;

namespace problema5
{
    public partial class Form1 : Form
    {
        public Service service;
        public Form1()
        {
            InitializeComponent();
        }

        public void setService(Service service)
        {
            this.service = service;
        }

        private void login(object sender, EventArgs e)
        {
            var username = textBox1.Text;
            var password = textBox2.Text;
            User user = service.findUser(username);
            if (user != null)
            {
                if (user.password == password)
                {
                    this.Close();
                    Main form = new Main();
                    form.setService(service);
                    form.Show();
                }
                else
                {
                    MessageBox.Show("Invalid password!");
                }
            }

            MessageBox.Show("This username is invalid, try again!");
        }
    }
}