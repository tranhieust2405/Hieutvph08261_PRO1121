package com.example.hieu.todoapp.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.RadioButton;

import com.example.hieu.todoapp.models.Task;

public class TaskCreatorDialogFragment extends TaskDialogFragment {
    private TaskCreatorDialogOnFinishedListener listener;

    public interface TaskCreatorDialogOnFinishedListener
            extends TaskDialogOnFinishedListener {
        void onTaskCreatorDialogFinished(Task task);
    }

    public static TaskDialogFragment newInstance() {
        Bundle args = new Bundle();

        TaskDialogFragment taskDialogFragment = new TaskCreatorDialogFragment();
        taskDialogFragment.setArguments(args);

        return taskDialogFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            listener = (TaskCreatorDialogOnFinishedListener) context;
        }
    }

    @Override
    public void onDetach() {
        listener = null;
        super.onDetach();
    }

    @Override
    void onSaveButtonClick() {
        super.onSaveButtonClick();

        if (getView() != null) {
            RadioButton rbPriority = (RadioButton) getView().findViewById(
                    rgPriority.getCheckedRadioButtonId()
            );
            String priority = rbPriority.getText().toString();
            String taskName = edTaskName.getText().toString();

            Task task = new Task(taskName, priority, getDateFromPicker(tpDueDate));
            listener.onTaskCreatorDialogFinished(task);
        }

        dismiss();
    }

    @Override
    void onDiscardButtonClick() {
        super.onDiscardButtonClick();
        dismiss();
    }
}
