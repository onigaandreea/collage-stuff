using System;
using System.ComponentModel;

namespace problema5
{
    partial class Main
{
    /// <summary>
    /// Required designer variable.
    /// </summary>
    private System.ComponentModel.IContainer components = null;

    /// <summary>
    /// Clean up any resources being used.
    /// </summary>
    /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
    protected override void Dispose(bool disposing)
    {
        if (disposing && (components != null))
        {
            components.Dispose();
        }

        base.Dispose(disposing);
    }

    #region Windows Form Designer generated code

    /// <summary>
    /// Required method for Designer support - do not modify
    /// the contents of this method with the code editor.
    /// </summary>
    private void InitializeComponent()
    {
        this.dataGridView1 = new System.Windows.Forms.DataGridView();
        this.dataGridView2 = new System.Windows.Forms.DataGridView();
        this.button1 = new System.Windows.Forms.Button();
        this.button2 = new System.Windows.Forms.Button();
        this.textBox1 = new System.Windows.Forms.TextBox();
        this.textBox3 = new System.Windows.Forms.TextBox();
        this.button3 = new System.Windows.Forms.Button();
        ((System.ComponentModel.ISupportInitialize)(this.dataGridView1)).BeginInit();
        ((System.ComponentModel.ISupportInitialize)(this.dataGridView2)).BeginInit();
        this.SuspendLayout();
        // 
        // dataGridView1
        // 
        this.dataGridView1.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
        this.dataGridView1.Location = new System.Drawing.Point(31, 26);
        this.dataGridView1.Name = "dataGridView1";
        this.dataGridView1.RowTemplate.Height = 24;
        this.dataGridView1.Size = new System.Drawing.Size(472, 251);
        this.dataGridView1.TabIndex = 0;
        this.dataGridView1.SelectionChanged += new System.EventHandler(this.populate_table1);
        // 
        // dataGridView2
        // 
        this.dataGridView2.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
        this.dataGridView2.Location = new System.Drawing.Point(528, 26);
        this.dataGridView2.Name = "dataGridView2";
        this.dataGridView2.RowTemplate.Height = 24;
        this.dataGridView2.Size = new System.Drawing.Size(247, 250);
        this.dataGridView2.TabIndex = 1;
        // 
        // button1
        // 
        this.button1.Location = new System.Drawing.Point(31, 299);
        this.button1.Name = "button1";
        this.button1.Size = new System.Drawing.Size(472, 32);
        this.button1.TabIndex = 2;
        this.button1.Text = "Add Participant";
        this.button1.UseVisualStyleBackColor = true;
        this.button1.Click += new System.EventHandler(this.handleAdd);
        // 
        // button2
        // 
        this.button2.Location = new System.Drawing.Point(528, 299);
        this.button2.Name = "button2";
        this.button2.Size = new System.Drawing.Size(247, 32);
        this.button2.TabIndex = 3;
        this.button2.Text = "Search Participants";
        this.button2.UseVisualStyleBackColor = true;
        this.button2.Click += new EventHandler(this.handleSearch);
        // 
        // textBox1
        // 
        this.textBox1.Location = new System.Drawing.Point(31, 357);
        this.textBox1.Name = "textBox1";
        this.textBox1.Size = new System.Drawing.Size(228, 22);
        this.textBox1.TabIndex = 4;
        this.textBox1.Text = "name";
        // 
        // textBox3
        // 
        this.textBox3.Location = new System.Drawing.Point(278, 357);
        this.textBox3.Name = "textBox3";
        this.textBox3.Size = new System.Drawing.Size(225, 22);
        this.textBox3.TabIndex = 6;
        this.textBox3.Text = "age";
        // 
        // button3
        // 
        this.button3.Location = new System.Drawing.Point(31, 403);
        this.button3.Name = "button3";
        this.button3.Size = new System.Drawing.Size(132, 25);
        this.button3.TabIndex = 7;
        this.button3.Text = "SignOut";
        this.button3.UseVisualStyleBackColor = true;
        this.button3.Click += new EventHandler(this.signOut);
        // 
        // Main
        // 
        this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
        this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
        this.ClientSize = new System.Drawing.Size(800, 450);
        this.Controls.Add(this.button3);
        this.Controls.Add(this.textBox3);
        this.Controls.Add(this.textBox1);
        this.Controls.Add(this.button2);
        this.Controls.Add(this.button1);
        this.Controls.Add(this.dataGridView2);
        this.Controls.Add(this.dataGridView1);
        this.Name = "Main";
        this.Text = "Form2";
        ((System.ComponentModel.ISupportInitialize)(this.dataGridView1)).EndInit();
        ((System.ComponentModel.ISupportInitialize)(this.dataGridView2)).EndInit();
        this.ResumeLayout(false);
        this.PerformLayout();
    }

    private System.Windows.Forms.Button button1;
    private System.Windows.Forms.Button button2;
    private System.Windows.Forms.TextBox textBox1;
    private System.Windows.Forms.TextBox textBox3;
    private System.Windows.Forms.Button button3;

    private System.Windows.Forms.DataGridView dataGridView2;

    private System.Windows.Forms.DataGridView dataGridView1;

    #endregion
}
}
