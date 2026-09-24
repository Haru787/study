import React, { useState } from 'react';

const STATUS_OPTIONS = ['未着手', '進行中', '完了'];

function StatusSelector({ currentStatus, taskId, onUpdateStatus }) {
  const [selected, setSelected] = useState(currentStatus);
  const [isEditing, setIsEditing] = useState(false);

  const handleApply = () => {
    onUpdateStatus(taskId, selected);
    setIsEditing(false);
  };

  const handleCancel = () => {
    setSelected(currentStatus);
    setIsEditing(false);
  };

  if (!isEditing) {
    return (
      <button
        onClick={() => setIsEditing(true)}
        style={{
          padding: '6px 14px',
          fontSize: '13px',
          backgroundColor: '#edf2f7',
          color: '#4a5568',
          border: '1px solid #e2e8f0',
          borderRadius: '4px',
          cursor: 'pointer',
        }}
      >
        ステータス変更
      </button>
    );
  }

  return (
    <div style={{ display: 'flex', flexDirection: 'column', gap: '6px' }}>
      <select
        value={selected}
        onChange={(e) => setSelected(e.target.value)}
        style={{
          padding: '6px',
          fontSize: '13px',
          border: '1px solid #e2e8f0',
          borderRadius: '4px',
        }}
      >
        {STATUS_OPTIONS.map((s) => (
          <option key={s} value={s}>
            {s}
          </option>
        ))}
      </select>
      <div style={{ display: 'flex', gap: '4px' }}>
        <button
          onClick={handleApply}
          style={{
            flex: 1,
            padding: '5px',
            fontSize: '12px',
            backgroundColor: '#4299e1',
            color: '#ffffff',
            border: 'none',
            borderRadius: '4px',
            cursor: 'pointer',
          }}
        >
          更新
        </button>
        <button
          onClick={handleCancel}
          style={{
            flex: 1,
            padding: '5px',
            fontSize: '12px',
            backgroundColor: '#e2e8f0',
            color: '#4a5568',
            border: 'none',
            borderRadius: '4px',
            cursor: 'pointer',
          }}
        >
          戻す
        </button>
      </div>
    </div>
  );
}

export default StatusSelector;
