import React from 'react';
import TaskCard from './TaskCard';
import StatusSelector from './StatusSelector';

function TaskList({ tasks, onUpdateStatus, onDeleteTask }) {
  if (tasks.length === 0) {
    return (
      <div style={{ textAlign: 'center', padding: '64px', color: '#a0aec0' }}>
        <p style={{ fontSize: '16px' }}>タスクがありません</p>
      </div>
    );
  }

  return (
    <div>
      <div style={{ fontSize: '14px', color: '#718096', marginBottom: '12px' }}>
        全 {tasks.length} 件
      </div>
      <div style={{ display: 'flex', flexDirection: 'column', gap: '12px' }}>
        {tasks.map((task) => (
          <div
            key={task.id}
            style={{
              border: '1px solid #e2e8f0',
              borderRadius: '8px',
              padding: '16px',
              backgroundColor: '#ffffff',
              boxShadow: '0 1px 2px rgba(0,0,0,0.05)',
              display: 'flex',
              justifyContent: 'space-between',
              alignItems: 'flex-start',
              gap: '16px',
            }}
          >
            <TaskCard task={task} />
            <div style={{ display: 'flex', flexDirection: 'column', gap: '8px', flexShrink: 0 }}>
              <StatusSelector
                currentStatus={task.status}
                taskId={task.id}
                onUpdateStatus={onUpdateStatus}
              />
              <button
                onClick={() => onDeleteTask(task.id)}
                style={{
                  padding: '6px 14px',
                  fontSize: '13px',
                  backgroundColor: '#fff5f5',
                  color: '#e53e3e',
                  border: '1px solid #feb2b2',
                  borderRadius: '4px',
                  cursor: 'pointer',
                }}
              >
                削除
              </button>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}

export default TaskList;
