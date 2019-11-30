package com.example.hieu.todoapp.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.hieu.todoapp.R;
import com.example.hieu.todoapp.adapters.TaskAdapter;
import com.example.hieu.todoapp.fragments.TaskCreatorDialogFragment;
import com.example.hieu.todoapp.fragments.TaskEditorDialogFragment;
import com.example.hieu.todoapp.fragments.TaskRemoveDialog;
import com.example.hieu.todoapp.models.Task;
import com.example.hieu.todoapp.repositories.LocalDBHandler;

public class MainActivity extends AppCompatActivity
        implements TaskEditorDialogFragment.TaskEditorDialogOnFinishedListener,
        TaskCreatorDialogFragment.TaskCreatorDialogOnFinishedListener,
        TaskRemoveDialog.TaskRemoveDialogListener,
        AdapterView.OnItemLongClickListener,
        AdapterView.OnItemClickListener,
        View.OnClickListener {
    private ListView lvTasks;
    private FloatingActionButton fab;

    private TaskAdapter adapter;

    private LocalDBHandler dbTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        lvTasks = (ListView) findViewById(R.id.list_task_item);
        lvTasks.setOnItemLongClickListener(this);
        lvTasks.setOnItemClickListener(this);

        fab = (FloatingActionButton) findViewById(R.id.FloatingActionButton);
        fab.setOnClickListener(this);

        loadTasks();
    }

    private void loadTasks() {
        dbTasks = LocalDBHandler.getInstance(this);
        adapter = new TaskAdapter(this, dbTasks.getTasks());
        lvTasks.setAdapter(adapter);
    }

    @Override
    protected void onPause() {
        if (dbTasks != null) {
            dbTasks.refreshTasks(adapter.getTasks());
        }
        super.onPause();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        TaskRemoveDialog.newInstance(position)
                .show(getSupportFragmentManager(), null);
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TaskEditorDialogFragment.newInstance(adapter.getTask(position), position)
                .show(getSupportFragmentManager(), null);
    }

    @Override
    public void onClick(View v) {
        TaskCreatorDialogFragment.newInstance()
                .show(getSupportFragmentManager(), null);
    }

    @Override
    public void onTaskCreatorDialogFinished(Task task) {
        adapter.addTask(0, task);
    }

    @Override
    public void onTaskEditorDialogFinished(Task task, int position) {
        adapter.modifyTask(position, task);
    }

    @Override
    protected void onDestroy() {
        dbTasks.close();
        super.onDestroy();
    }

    @Override
    public void onOKButtonClick(int position) {
        adapter.removeTask(position);
    }

    @Override
    public void onCancelButtonClick(int position) {
    }
}