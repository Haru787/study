import React, { useState, useReducer } from 'react';
import TaskList from './TaskList';
import TaskForm from './TaskForm';
import NotificationBar from './NotificationBar';
import { taskReducer, initialState } from './taskReducer';

function TaskApp() {
  const [state, dispatch] = useReducer(taskReducer, initialState);
  const [filterAssigneeId, setFilterAssigneeId] = useState(null);
  const [notification, setNotification] = useState(null);

  const handleAddTask = (newTask) => {
    dispatch({ type: 'ADD_TASK', payload: newTask });
    setNotification({ message: `タスク「${newTask.title}」を追加しました`, type: 'success' });
  };

  const handleUpdateStatus = (taskId, newStatus) => {
    dispatch({ type: 'UPDATE_STATUS', payload: { taskId, newStatus } });
    setNotification({ message: 'ステータスを更新しました', type: 'info' });
  };

  const handleDeleteTask = (taskId) => {
    const target = state.tasks.find((t) => t.id === taskId);
    dispatch({ type: 'DELETE_TASK', payload: { taskId } });
    setNotification({ message: `タスク「${target?.title}」を削除しました`, type: 'warning' });
  };

  const handleClearNotification = () => {
    setNotification(null);
  };

  const members = [
    { id: 101, name: '田中 太郎' },
    { id: 102, name: '鈴木 花子' },
    { id: 103, name: '山田 美咲' },
    { id: 104, name: '伊藤 健一' },
  ];

  const filteredTasks = filterAssigneeId
    ? state.tasks.filter((task) => task.assigneeId === filterAssigneeId)
    : state.tasks;

  return (
    <div style={{ maxWidth: '960px', margin: '0 auto', padding: '24px', fontFamily: 'sans-serif' }}>
      {notification && (
        <NotificationBar
          message={notification.message}
          type={notification.type}
          onClose={handleClearNotification}
        />
      )}
      <div style={{ marginBottom: '20px' }}>
        <h1 style={{ fontSize: '22px', fontWeight: 'bold', color: '#1a202c', marginBottom: '4px' }}>
          社内タスク管理
        </h1>
        <p style={{ fontSize: '13px', color: '#718096' }}>
          全 {state.tasks.length} 件 / 表示中 {filteredTasks.length} 件
        </p>
      </div>
      <div style={{ display: 'flex', gap: '8px', marginBottom: '16px', flexWrap: 'wrap', alignItems: 'center' }}>
        <span style={{ fontSize: '13px', color: '#718096' }}>担当者で絞り込み:</span>
        <button
          onClick={() => setFilterAssigneeId(null)}
          style={{
            padding: '5px 12px',
            fontSize: '13px',
            border: '1px solid',
            borderColor: filterAssigneeId === null ? '#4299e1' : '#e2e8f0',
            borderRadius: '9999px',
            backgroundColor: filterAssigneeId === null ? '#ebf8ff' : '#ffffff',
            color: filterAssigneeId === null ? '#2b6cb0' : '#718096',
            cursor: 'pointer',
          }}
        >
          全員
        </button>
        {members.map((member) => (
          <button
            key={member.id}
            onClick={() => setFilterAssigneeId(member.id)}
            style={{
              padding: '5px 12px',
              fontSize: '13px',
              border: '1px solid',
              borderColor: filterAssigneeId === member.id ? '#4299e1' : '#e2e8f0',
              borderRadius: '9999px',
              backgroundColor: filterAssigneeId === member.id ? '#ebf8ff' : '#ffffff',
              color: filterAssigneeId === member.id ? '#2b6cb0' : '#718096',
              cursor: 'pointer',
            }}
          >
            {member.name}
          </button>
        ))}
      </div>
      <TaskForm onSubmit={handleAddTask} />
      <TaskList
        tasks={filteredTasks}
        onUpdateStatus={handleUpdateStatus}
        onDeleteTask={handleDeleteTask}
      />
    </div>
  );
}

export default TaskApp;
