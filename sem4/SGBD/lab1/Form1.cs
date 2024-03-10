using System;
using System.Data;
using System.Windows.Forms;
using System.Data.SqlClient;

namespace lab1
{
    public partial class Form1 : Form
    {
        SqlConnection connection = new SqlConnection("Data Source=ANDREEA\\SQLEXPRESS; " +
                                                     "Initial Catalog=Biblioteca;Integrated Security=True;") ;
        DataSet ds = new DataSet();
        DataSet ds1 = new DataSet();
        SqlDataAdapter parentAdapter = new SqlDataAdapter();
        SqlDataAdapter childAdapter = new SqlDataAdapter();
        BindingSource parentBS = new BindingSource();
        BindingSource childBS = new BindingSource();

        public Form1()
        {
            InitializeComponent();
        }

        //Afisare date din tabela Autor
        private void button1_Click_1(object sender, EventArgs e)
        {
            parentAdapter.SelectCommand = new SqlCommand("SELECT * FROM Autor", connection);
            ds.Clear();
            parentAdapter.Fill(ds, "Autor");
            dataGridViewParent.DataSource = ds.Tables[0];
            parentBS.DataSource = ds.Tables[0];
        }

        private void LoadBooksByAuthor(int id)
        {
            childAdapter.SelectCommand = new SqlCommand("SELECT * FROM Carte WHERE idA = @ida", connection);
            childAdapter.SelectCommand.Parameters.Add("@ida", SqlDbType.Int).Value = id;
            ds1.Clear();
            childAdapter.Fill(ds1, "Carte");
            dataGridViewChild.DataSource = ds1.Tables[0];
            childBS.DataSource = ds1.Tables[0];
        }
        
        private void dataGridView1_SelectionChanged_1(object sender, EventArgs e)
        {
            if (dataGridViewParent.SelectedRows.Count != 1)
                return;
            try
            {
                int idSelectedAuthor = Int32.Parse(dataGridViewParent.SelectedRows[0].Cells[0].Value.ToString());
                textBox2.Text = idSelectedAuthor.ToString();
                LoadBooksByAuthor(idSelectedAuthor);
            }
            catch(Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }
        //adaugare inregistrare
        private void button4_Click(object sender, EventArgs e)
        {
            try
            {
                int x;
                childAdapter.InsertCommand =
                    new SqlCommand("INSERT  INTO  Carte  (titlu,  idA,  idSe, nr_exemplare) VALUES(@t,@a, @s, @e)", connection);
                childAdapter.InsertCommand.Parameters.Add("@t", SqlDbType.VarChar).Value = textBox1.Text;
                childAdapter.InsertCommand.Parameters.Add("@a", SqlDbType.Int).Value = Int32.Parse(textBox2.Text);
                childAdapter.InsertCommand.Parameters.Add("@s", SqlDbType.Int).Value = Int32.Parse(textBox4.Text);
                childAdapter.InsertCommand.Parameters.Add("@e", SqlDbType.Int).Value = Int32.Parse(textBox3.Text);
                connection.Open();
                x = childAdapter.InsertCommand.ExecuteNonQuery();
                connection.Close(); // already inserted -apear in the list
                ds1.Clear();
                childAdapter.Fill(ds1);
                dataGridViewChild.DataSource = ds1.Tables[0];

                if (x >= 1)
                {
                    MessageBox.Show("Inserted Succesfull to the Database");
                }
            
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
                connection.Close();
            }
        }
        //stergere inregistrare
        private void button2_Click(object sender, EventArgs e) 
        {
            DialogResult dr;
            dr = MessageBox.Show("Are you sure?\n You cannot undo this operation", "Confirm Deletion", MessageBoxButtons.YesNo);
            if (dr == DialogResult.Yes)
            {
                try
                {
                    childAdapter.DeleteCommand = new SqlCommand("DELETE FROM Carte WHERE idCa=@idc", connection);
                    childAdapter.DeleteCommand.Parameters.Add("@idc", SqlDbType.VarChar).Value = Int32.Parse(dataGridViewChild.SelectedRows[0].Cells[0].Value.ToString());

                    connection.Open();
                    childAdapter.DeleteCommand.ExecuteNonQuery();
                    connection.Close();

                    ds1.Clear();
                    childAdapter.Fill(ds1);
                }
                catch (Exception ex)
                {
                    MessageBox.Show(ex.Message);
                    connection.Close();
                }
            }
            else
            {
                MessageBox.Show("Deletion aborded");
            }
        }

        //updatare inregistrare
        private void button3_Click(object sender, EventArgs e)
        {
            try
            {
                int x;
                childAdapter.UpdateCommand = new SqlCommand("UPDATE Carte SET titlu=@t, idA=@a, idSe=@s, nr_exemplare=@ex WHERE idCa=@c", connection);
                childAdapter.UpdateCommand.Parameters.Add("@t", SqlDbType.VarChar).Value = textBox1.Text;
                childAdapter.UpdateCommand.Parameters.Add("@a", SqlDbType.VarChar).Value = Int32.Parse(textBox2.Text);
                childAdapter.UpdateCommand.Parameters.Add("@s", SqlDbType.VarChar).Value = Int32.Parse(textBox4.Text);
                childAdapter.UpdateCommand.Parameters.Add("@ex", SqlDbType.VarChar).Value = Int32.Parse(textBox3.Text);
                int idSelectedBook = Int32.Parse(dataGridViewChild.SelectedRows[0].Cells[0].Value.ToString());
                childAdapter.UpdateCommand.Parameters.Add("@c", SqlDbType.VarChar).Value = idSelectedBook;

                connection.Open();
                x = childAdapter.UpdateCommand.ExecuteNonQuery();
                connection.Close();
                
                ds1.Clear();
                childAdapter.Fill(ds1);

                if (x >= 1)
                {
                    MessageBox.Show("The record has been updated");
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
                connection.Close();
            }
        }

        

        private void dataGridView1_SelectionChanged(object sender, EventArgs e)
        {
            try
            {
                if (dataGridViewChild.SelectedRows.Count > 0)
                {
                    string titlu = dataGridViewChild.SelectedRows[0].Cells[1].Value.ToString();
                    string idAutor = dataGridViewChild.SelectedRows[0].Cells[2].Value.ToString();
                    string idSectiune = dataGridViewChild.SelectedRows[0].Cells[3].Value.ToString();
                    string nr_exemplare = dataGridViewChild.SelectedRows[0].Cells[4].Value.ToString();
                    
                    textBox1.Text = titlu;
                    textBox2.Text = idAutor;
                    textBox4.Text = idSectiune;
                    textBox3.Text = nr_exemplare;
                }
            }
            catch (Exception ex )
            {
                MessageBox.Show(ex.Message);    
            }
        }
    }
}