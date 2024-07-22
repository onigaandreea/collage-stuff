using System.Windows.Forms;

namespace Client
{
    public partial class login : Form
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
                    this.Hide();
                    Form2 form = new Form2();
                    form.setService(service);
                    form.Show();
                }
                else
                {
                    MessageBox.Show("Invalid password!");
                }
            }
            else
            {
                MessageBox.Show("This username is invalid, try again!");   
            }
        }
    }
}