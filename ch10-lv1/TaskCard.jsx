import React from 'react';

const priorityColors = {
  高: '#fc8181',
  中: '#f6ad55',
  低: '#68d391',
};

const statusColors = {
  未着手: '#e2e8f0',
  進行中: '#bee3f8',
  完了: '#c6f6d5',
};

function TaskCard({ task, onCompleteTask, onDeleteTask }) {
  return (
    <div
      style={{
        border: '1px solid #e2e8f0',
        borderRadius: '8px',
        padding: '16px',
        backgroundColor: '#ffffff',
        boxShadow: '0 1px 3px rgba(0,0,0,0.1)',
      }}
    >
      <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'flex-start' }}>
        <div style={{ flex: 1 }}>
          <div style={{ display: 'flex', alignItems: 'center', gap: '8px', marginBottom: '8px' }}>
            <h3 style={{ fontSize: '16px', fontWeight: '600', color: '#2d3748', margin: 0 }}>
              {task.title}
            </h3>
            <span
              style={{
                fontSize: '12px',
                padding: '2px 8px',
                borderRadius: '9999px',
                backgroundColor: priorityColors[task.priority] || '#e2e8f0',
                color: '#2d3748',
              }}
            >
              {task.priority}優先
            </span>
            <span
              style={{
                fontSize: '12px',
                padding: '2px 8px',
                borderRadius: '9999px',
                backgroundColor: statusColors[task.status] || '#e2e8f0',
                color: '#2d3748',
              }}
            >
              {task.status}
            </span>
          </div>
          <p style={{ fontSize: '14px', color: '#718096', marginBottom: '8px' }}>
            {task.description}
          </p>
          <div style={{ fontSize: '13px', color: '#a0aec0', display: 'flex', gap: '16px' }}>
            <span>担当: {task.assignee}</span>
            <span>期限: {task.dueDate}</span>
            <span>作成: {task.createdAt}</span>
          </div>
        </div>
        <div style={{ display: 'flex', flexDirection: 'column', gap: '8px', marginLeft: '16px' }}>
          <button
            onClick={() => onCompleteTask(task.id)}
            disabled={task.status === '完了'}
            style={{
              padding: '6px 14px',
              fontSize: '13px',
              backgroundColor: task.status === '完了' ? '#e2e8f0' : '#4299e1',
              color: task.status === '完了' ? '#a0aec0' : '#ffffff',
              border: 'none',
              borderRadius: '4px',
              cursor: task.status === '完了' ? 'not-allowed' : 'pointer',
            }}
          >
            完了にする
          </button>
          <button
            onClick={() => onDeleteTask(task.id)}
            style={{
              padding: '6px 14px',
              fontSize: '13px',
              backgroundColor: '#fff5f5',
              color: '#fc8181',
              border: '1px solid #feb2b2',
              borderRadius: '4px',
              cursor: 'pointer',
            }}
          >
            削除
          </button>
        </div>
      </div>
    </div>
  );
}

export default TaskCard;