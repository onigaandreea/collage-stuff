using System;
using System.Windows.Forms;
using model;
using service;

namespace problema5;

public partial class Main : Form
{
    public Main()
    {
        InitializeComponent();
    }

    public Service service;

    public void setService(Service service)
    {
        this.service = service;
    }


    public void populate_table1(object sender, EventArgs e)
    {
        this.dataGridView1.ColumnCount = 4;
        this.dataGridView1.Columns[0].Name = "Description";
        this.dataGridView1.Columns[1].Name = "Minimal Age";
        this.dataGridView1.Columns[2].Name = "Maximal Age";
        this.dataGridView1.Columns[3].Name = "NoParticipant";

        foreach (var t in service.findAllTasks())
        {
            var index = this.dataGridView1.Rows.Add();
            this.dataGridView1.Rows[index].Cells[0].Value = t.task.description;
            this.dataGridView1.Rows[index].Cells[1].Value = t.task.ageCatStart;
            this.dataGridView1.Rows[index].Cells[2].Value = t.task.ageCatEnd;
            this.dataGridView1.Rows[index].Cells[3].Value = t.number;
        }
    }
    
    public void populate_table2(Task task)
    {
        this.dataGridView2.ColumnCount = 2;
        this.dataGridView2.Columns[0].Name = "Name";
        this.dataGridView2.Columns[1].Name = "Age";
        
        
        
        foreach(Child e in service.findParticipants(task))
        {
            var index = this.dataGridView2.Rows.Add();
            this.dataGridView2.Rows[index].Cells[0].Value = e.name;
            this.dataGridView2.Rows[index].Cells[1].Value = e.age;
        }
    }

    public void signOut(object sender, EventArgs e)
    {
        Form1 form = new Form1();
        form.setService(this.service);
        form.Show();
        this.Close();
    }

    public void handleSearch(object sender, EventArgs e)
    {
        string desc = (string) dataGridView1.SelectedRows[0].Cells[0].Value;
        int agemin = (int)dataGridView1.SelectedRows[0].Cells[1].Value;
        int agemax = (int)dataGridView1.SelectedRows[0].Cells[2].Value;

        Task task = new Task(desc, agemin, agemax);
        populate_table2(task);
    }

    public void handleAdd(object sender, EventArgs e)
    {
        string name = textBox1.Text;
        int age = Int32.Parse(textBox3.Text);
        string desc = (string) dataGridView1.SelectedRows[0].Cells[0].Value;
        int agemin = (int)dataGridView1.SelectedRows[0].Cells[1].Value;
        int agemax = (int)dataGridView1.SelectedRows[0].Cells[2].Value;

        Task task = new Task(desc, agemin, agemax);
        service.addParticipation(name, age, task);
    }
}