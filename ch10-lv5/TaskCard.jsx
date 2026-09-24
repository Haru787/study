import React, { useState } from 'react';

const priorityConfig = {
  高: { label: '高優先', color: '#c53030', bg: '#fff5f5', border: '#feb2b2' },
  中: { label: '中優先', color: '#c05621', bg: '#fffaf0', border: '#fbd38d' },
  低: { label: '低優先', color: '#276749', bg: '#f0fff4', border: '#9ae6b4' },
};

const STATUS_OPTIONS = ['未着手', '進行中', '完了'];

function TaskCard({ task, onUpdateStatus, onDeleteTask }) {
  const [editingStatus, setEditingStatus] = useState(false);
  const [selectedStatus, setSelectedStatus] = useState(task.status);

  const priority = priorityConfig[task.priority] || {
    label: task.priority,
    color: '#4a5568',
    bg: '#edf2f7',
    border: '#e2e8f0',
  };

  const currentUserId = 101;

  const isMyTask = task.assigneeId === currentUserId;

  const handleApply = () => {
    onUpdateStatus(task.id, selectedStatus);
    setEditingStatus(false);
  };

  return (
    <div
      style={{
        border: `1px solid ${priority.border}`,
        borderRadius: '8px',
        padding: '14px 16px',
        backgroundColor: isMyTask ? '#fffdf0' : '#ffffff',
        boxShadow: '0 1px 3px rgba(0,0,0,0.06)',
        borderLeft: isMyTask ? '4px solid #f6ad55' : `1px solid ${priority.border}`,
      }}
    >
      <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'flex-start', gap: '12px' }}>
        <div style={{ flex: 1 }}>
          <div style={{ display: 'flex', alignItems: 'center', gap: '8px', marginBottom: '6px', flexWrap: 'wrap' }}>
            {isMyTask && (
              <span style={{ fontSize: '11px', color: '#c05621', fontWeight: '700' }}>
                自分のタスク
              </span>
            )}
            <h3 style={{ fontSize: '15px', fontWeight: '600', color: '#2d3748', margin: 0 }}>
              {task.title}
            </h3>
            <span
              style={{
                fontSize: '11px',
                padding: '2px 8px',
                borderRadius: '9999px',
                backgroundColor: priority.bg,
                color: priority.color,
                border: `1px solid ${priority.border}`,
                fontWeight: '600',
              }}
            >
              {priority.label}
            </span>
          </div>
          {task.description && (
            <p style={{ fontSize: '13px', color: '#718096', marginBottom: '8px', lineHeight: '1.5' }}>
              {task.description}
            </p>
          )}
          <div style={{ display: 'flex', gap: '16px', fontSize: '12px', color: '#a0aec0', flexWrap: 'wrap' }}>
            <span>担当: {task.assignee}（ID: {task.assigneeId}）</span>
            <span>期限: {task.dueDate}</span>
            <span>作成: {task.createdAt}</span>
          </div>
        </div>
        <div style={{ display: 'flex', flexDirection: 'column', gap: '6px', flexShrink: 0 }}>
          {editingStatus ? (
            <div style={{ display: 'flex', flexDirection: 'column', gap: '4px' }}>
              <select
                value={selectedStatus}
                onChange={(e) => setSelectedStatus(e.target.value)}
                style={{ padding: '5px', fontSize: '13px', border: '1px solid #e2e8f0', borderRadius: '4px' }}
              >
                {STATUS_OPTIONS.map((s) => (
                  <option key={s} value={s}>{s}</option>
                ))}
              </select>
              <div style={{ display: 'flex', gap: '4px' }}>
                <button
                  onClick={handleApply}
                  style={{ flex: 1, padding: '4px', fontSize: '12px', backgroundColor: '#4299e1', color: '#fff', border: 'none', borderRadius: '4px', cursor: 'pointer' }}
                >
                  更新
                </button>
                <button
                  onClick={() => { setSelectedStatus(task.status); setEditingStatus(false); }}
                  style={{ flex: 1, padding: '4px', fontSize: '12px', backgroundColor: '#e2e8f0', color: '#4a5568', border: 'none', borderRadius: '4px', cursor: 'pointer' }}
                >
                  戻す
                </button>
              </div>
            </div>
          ) : (
            <button
              onClick={() => setEditingStatus(true)}
              style={{ padding: '6px 12px', fontSize: '13px', backgroundColor: '#edf2f7', color: '#4a5568', border: '1px solid #e2e8f0', borderRadius: '4px', cursor: 'pointer' }}
            >
              ステータス変更
            </button>
          )}
          <button
            onClick={() => onDeleteTask(task.id)}
            style={{ padding: '6px 12px', fontSize: '13px', backgroundColor: '#fff5f5', color: '#e53e3e', border: '1px solid #feb2b2', borderRadius: '4px', cursor: 'pointer' }}
          >
            削除
          </button>
        </div>
      </div>
    </div>
  );
}

export default TaskCard;
