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
using services;

namespace Client
{
    public partial class Form1 : Form
    {
        private CompetitionController Controller;
        public Form1(CompetitionController ctrl)
        {
            InitializeComponent();
            this.Controller = ctrl;
        }

        private void login(object sender, EventArgs e)
        {
            var username = textBox1.Text;
            var password = textBox2.Text;
            try
            {
                Controller.login(username);
                Form2 form = new Form2(Controller);
                form.Show();
                this.Hide();
            }
            catch (Exception exception)
            {
                MessageBox.Show(this, "Login Error " + exception.Message, "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                throw;
            }
            
        }
    }
}