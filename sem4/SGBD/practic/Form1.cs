using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.SqlClient;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace practic
{
    public partial class Form1 : Form
    {
        SqlConnection connection = new SqlConnection("Data Source=ANDREEA\\SQLEXPRESS; " +
                                                     "Initial Catalog=S6;Integrated Security=True;") ;
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
            parentAdapter.SelectCommand = new SqlCommand("SELECT * FROM Echipe", connection);
            ds.Clear();
            parentAdapter.Fill(ds, "Echipe");
            dataGridViewParent.DataSource = ds.Tables[0];
            parentBS.DataSource = ds.Tables[0];
        }

        private void LoadPrizeByTeam(int id)
        {
            childAdapter.SelectCommand = new SqlCommand("SELECT * FROM Premii WHERE Eid = @ida", connection);
            childAdapter.SelectCommand.Parameters.Add("@ida", SqlDbType.Int).Value = id;
            ds1.Clear();
            childAdapter.Fill(ds1, "Premii");
            dataGridViewChild.DataSource = ds1.Tables[0];
            childBS.DataSource = ds1.Tables[0];
        }
        
        private void dataGridView1_SelectionChanged_1(object sender, EventArgs e)
        {
            if (dataGridViewParent.SelectedRows.Count != 1)
                return;
            try
            {
                int idSelected = Int32.Parse(dataGridViewParent.SelectedRows[0].Cells[0].Value.ToString());
                textBox3.Text = idSelected.ToString();
                LoadPrizeByTeam(idSelected);
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
                    new SqlCommand("INSERT  INTO  Premii  (denumire, descriere, valoare, Eid, Tid) VALUES(@de,@d, @v, @e, @t )", connection);
                childAdapter.InsertCommand.Parameters.Add("@de", SqlDbType.VarChar).Value = textBox1.Text;
                childAdapter.InsertCommand.Parameters.Add("@d", SqlDbType.VarChar).Value = textBox2.Text;
                childAdapter.InsertCommand.Parameters.Add("@v", SqlDbType.Int).Value = Int32.Parse(textBox4.Text);
                childAdapter.InsertCommand.Parameters.Add("@e", SqlDbType.Int).Value = Int32.Parse(textBox3.Text);
                childAdapter.InsertCommand.Parameters.Add("@t", SqlDbType.Int).Value = Int32.Parse(textBox6.Text);
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
                    childAdapter.DeleteCommand = new SqlCommand("DELETE FROM Premii WHERE Pid=@id", connection);
                    childAdapter.DeleteCommand.Parameters.Add("@id", SqlDbType.VarChar).Value = Int32.Parse(dataGridViewChild.SelectedRows[0].Cells[0].Value.ToString());

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
                childAdapter.UpdateCommand = new SqlCommand("UPDATE Premii SET denumire=@de, descriere=@d, valoare=@v, Eid=@e, Tid=@t WHERE Pid=@p", connection);
                childAdapter.UpdateCommand.Parameters.Add("@de", SqlDbType.VarChar).Value = textBox1.Text;
                childAdapter.UpdateCommand.Parameters.Add("@d", SqlDbType.VarChar).Value = textBox2.Text;
                childAdapter.UpdateCommand.Parameters.Add("@v", SqlDbType.Int).Value = Int32.Parse(textBox4.Text);
                childAdapter.UpdateCommand.Parameters.Add("@e", SqlDbType.Int).Value = Int32.Parse(textBox3.Text);
                childAdapter.UpdateCommand.Parameters.Add("@t", SqlDbType.Int).Value = Int32.Parse(textBox6.Text);
                int idSelected = Int32.Parse(dataGridViewChild.SelectedRows[0].Cells[0].Value.ToString());
                childAdapter.UpdateCommand.Parameters.Add("@p", SqlDbType.VarChar).Value = idSelected;

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
                    string denumire = dataGridViewChild.SelectedRows[0].Cells[1].Value.ToString();
                    string descriere = dataGridViewChild.SelectedRows[0].Cells[2].Value.ToString();
                    string valoare = dataGridViewChild.SelectedRows[0].Cells[3].Value.ToString();
                    string idE = dataGridViewChild.SelectedRows[0].Cells[4].Value.ToString();
                    string idT = dataGridViewChild.SelectedRows[0].Cells[5].Value.ToString();
                    
                    textBox1.Text = denumire;
                    textBox2.Text = descriere;
                    textBox4.Text = valoare;
                    textBox3.Text = idE;
                    textBox6.Text = idT;
                }
            }
            catch (Exception ex )
            {
                MessageBox.Show(ex.Message);    
            }
        }
        
    }
}