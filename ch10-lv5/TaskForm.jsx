import React, { useState } from 'react';

function TaskForm({ onSubmit }) {
  const [isOpen, setIsOpen] = useState(false);
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');
  const [assignee, setAssignee] = useState('');
  const [assigneeId, setAssigneeId] = useState('');
  const [priority, setPriority] = useState('中');
  const [dueDate, setDueDate] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!title.trim() || !assignee.trim() || !dueDate) return;
    onSubmit({
      title: title.trim(),
      description: description.trim(),
      assignee: assignee.trim(),
      assigneeId: assigneeId ? parseInt(assigneeId, 10) : null,
      priority,
      dueDate,
      status: '未着手',
    });
    setTitle('');
    setDescription('');
    setAssignee('');
    setAssigneeId('');
    setPriority('中');
    setDueDate('');
    setIsOpen(false);
  };

  if (!isOpen) {
    return (
      <div style={{ marginBottom: '20px' }}>
        <button
          onClick={() => setIsOpen(true)}
          style={{
            padding: '10px 20px',
            backgroundColor: '#48bb78',
            color: '#ffffff',
            border: 'none',
            borderRadius: '6px',
            fontSize: '14px',
            cursor: 'pointer',
          }}
        >
          ＋ 新しいタスクを追加
        </button>
      </div>
    );
  }

  return (
    <form
      onSubmit={handleSubmit}
      style={{
        border: '1px solid #e2e8f0',
        borderRadius: '8px',
        padding: '20px',
        marginBottom: '20px',
        backgroundColor: '#f7fafc',
      }}
    >
      <h2 style={{ fontSize: '16px', fontWeight: '600', marginBottom: '16px', color: '#2d3748' }}>
        新規タスク追加
      </h2>
      <div style={{ display: 'grid', gridTemplateColumns: '1fr 1fr', gap: '12px' }}>
        <div>
          <label htmlFor="form-title" style={{ display: 'block', fontSize: '13px', marginBottom: '4px', color: '#4a5568' }}>
            タイトル <span style={{ color: '#fc8181' }}>*</span>
          </label>
          <input
            id="form-title"
            type="text"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
            placeholder="タスクタイトルを入力"
            required
            style={{ width: '100%', padding: '8px', border: '1px solid #e2e8f0', borderRadius: '4px', fontSize: '14px', boxSizing: 'border-box' }}
          />
        </div>
        <div>
          <label htmlFor="form-assignee" style={{ display: 'block', fontSize: '13px', marginBottom: '4px', color: '#4a5568' }}>
            担当者 <span style={{ color: '#fc8181' }}>*</span>
          </label>
          <input
            id="form-assignee"
            type="text"
            value={assignee}
            onChange={(e) => setAssignee(e.target.value)}
            placeholder="担当者名を入力"
            required
            style={{ width: '100%', padding: '8px', border: '1px solid #e2e8f0', borderRadius: '4px', fontSize: '14px', boxSizing: 'border-box' }}
          />
        </div>
        <div>
          <label htmlFor="form-assignee-id" style={{ display: 'block', fontSize: '13px', marginBottom: '4px', color: '#4a5568' }}>
            担当者ID
          </label>
          <input
            id="form-assignee-id"
            type="number"
            value={assigneeId}
            onChange={(e) => setAssigneeId(e.target.value)}
            placeholder="例: 101"
            style={{ width: '100%', padding: '8px', border: '1px solid #e2e8f0', borderRadius: '4px', fontSize: '14px', boxSizing: 'border-box' }}
          />
        </div>
        <div>
          <label htmlFor="form-priority" style={{ display: 'block', fontSize: '13px', marginBottom: '4px', color: '#4a5568' }}>
            優先度
          </label>
          <select
            id="form-priority"
            value={priority}
            onChange={(e) => setPriority(e.target.value)}
            style={{ width: '100%', padding: '8px', border: '1px solid #e2e8f0', borderRadius: '4px', fontSize: '14px', boxSizing: 'border-box' }}
          >
            <option value="高">高</option>
            <option value="中">中</option>
            <option value="低">低</option>
          </select>
        </div>
        <div>
          <label htmlFor="form-due" style={{ display: 'block', fontSize: '13px', marginBottom: '4px', color: '#4a5568' }}>
            期限 <span style={{ color: '#fc8181' }}>*</span>
          </label>
          <input
            id="form-due"
            type="date"
            value={dueDate}
            onChange={(e) => setDueDate(e.target.value)}
            required
            style={{ width: '100%', padding: '8px', border: '1px solid #e2e8f0', borderRadius: '4px', fontSize: '14px', boxSizing: 'border-box' }}
          />
        </div>
        <div style={{ gridColumn: '1 / -1' }}>
          <label htmlFor="form-desc" style={{ display: 'block', fontSize: '13px', marginBottom: '4px', color: '#4a5568' }}>
            説明
          </label>
          <textarea
            id="form-desc"
            value={description}
            onChange={(e) => setDescription(e.target.value)}
            placeholder="タスクの詳細を入力（任意）"
            rows={3}
            style={{ width: '100%', padding: '8px', border: '1px solid #e2e8f0', borderRadius: '4px', fontSize: '14px', boxSizing: 'border-box', resize: 'vertical' }}
          />
        </div>
      </div>
      <div style={{ display: 'flex', gap: '8px', marginTop: '16px' }}>
        <button
          type="submit"
          style={{ padding: '8px 20px', backgroundColor: '#4299e1', color: '#ffffff', border: 'none', borderRadius: '4px', fontSize: '14px', cursor: 'pointer' }}
        >
          追加する
        </button>
        <button
          type="button"
          onClick={() => setIsOpen(false)}
          style={{ padding: '8px 20px', backgroundColor: '#e2e8f0', color: '#4a5568', border: 'none', borderRadius: '4px', fontSize: '14px', cursor: 'pointer' }}
        >
          キャンセル
        </button>
      </div>
    </form>
  );
}

export default TaskForm;
