using System;
using System.Collections.Generic;
using System.Windows.Forms;
using model;
using services;

namespace Client
{
    public partial class Form2 : Form
    {
        public CompetitionController ctrl;

        public Form2(CompetitionController controller)
        {
            InitializeComponent();
            this.ctrl = controller;
            populate_table1();
            ctrl.UpdateEvent += userUpdate;

        }
        
        public void userUpdate(object sender, CompetitionUserEvent e)
        {
            if(e.UserEventType == UserEvent.ParticipantAdded)
            {
                this.BeginInvoke(new updateGridViewCallBack(this.populate_table1));
            }
        }
    
        public void populate_table1()
        {
            this.dataGridView1.Rows.Clear();
            this.dataGridView1.ColumnCount = 5;
            this.dataGridView1.Columns[0].Name = "TaskID";
            this.dataGridView1.Columns[1].Name = "Description";
            this.dataGridView1.Columns[2].Name = "Minimal Age";
            this.dataGridView1.Columns[3].Name = "Maximal Age";
            this.dataGridView1.Columns[4].Name = "NoParticipant";

            foreach (var t in ctrl.listTasks())
            {
                var index = this.dataGridView1.Rows.Add();
                this.dataGridView1.Rows[index].Cells[0].Value = t.ID;
                this.dataGridView1.Rows[index].Cells[1].Value = t.description;
                this.dataGridView1.Rows[index].Cells[2].Value = t.ageCatStart;
                this.dataGridView1.Rows[index].Cells[3].Value = t.ageCatEnd;
                this.dataGridView1.Rows[index].Cells[4].Value = t.nrChildren;
            }
        }
    
        public delegate void updateGridViewCallBack();
        public void populate_table2()
        {
            this.dataGridView2.Rows.Clear();
            this.dataGridView2.ColumnCount = 2;
            this.dataGridView2.Columns[0].Name = "Name";
            this.dataGridView2.Columns[1].Name = "Age";
        
            if(dataGridView1.SelectedRows.Count != 1)
                return;
        
            int id = Int32.Parse(dataGridView1.SelectedRows[0].Cells[0].Value.ToString());
            string desc = dataGridView1.SelectedRows[0].Cells[1].Value.ToString();
            int agemin = Int32.Parse(dataGridView1.SelectedRows[0].Cells[2].Value.ToString());
            int agemax = Int32.Parse(dataGridView1.SelectedRows[0].Cells[3].Value.ToString());

            Task task = new Task(desc, agemin, agemax);
            task.ID = id;
            List<Child> children = ctrl.listParticipants(task);
            Console.WriteLine("children {0}", children.Count);
            foreach(Child c in children)
            {
                var index = this.dataGridView2.Rows.Add();
                Console.WriteLine("c.name {0} ddd",c);
                this.dataGridView2.Rows[index].Cells[0].Value = c.name;
                this.dataGridView2.Rows[index].Cells[1].Value = c.age;
            }
        }
        
        private void viewButton_Click(object sender, EventArgs e)
        {
            populate_table2();
        }

        public void signOut(object sender, EventArgs e)
        {
            ctrl.logout();
            this.Hide();
        }
    

        public void handleAdd(object sender, EventArgs e)
        {
            string name = textBox1.Text;
            int age = Int32.Parse(textBox3.Text);
            int id = Int32.Parse(dataGridView1.SelectedRows[0].Cells[0].Value.ToString());
            string desc = dataGridView1.SelectedRows[0].Cells[1].Value.ToString();
            int agemin = Int32.Parse(dataGridView1.SelectedRows[0].Cells[2].Value.ToString());
            int agemax = Int32.Parse(dataGridView1.SelectedRows[0].Cells[3].Value.ToString());

            Task task = new Task(desc, agemin, agemax);
            task.ID = id;
            
            ctrl.addParticipant(name, age, task);
            populate_table1();
        }
    }
}