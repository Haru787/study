import React, { useState } from 'react';

function TaskHeader({ stats, onAddTask }) {
  const [isFormOpen, setIsFormOpen] = useState(false);
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');
  const [assignee, setAssignee] = useState('');
  const [priority, setPriority] = useState('中');
  const [dueDate, setDueDate] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!title.trim() || !assignee.trim() || !dueDate) return;
    onAddTask({
      title: title.trim(),
      description: description.trim(),
      assignee: assignee.trim(),
      priority,
      dueDate,
      status: '未着手',
    });
    setTitle('');
    setDescription('');
    setAssignee('');
    setPriority('中');
    setDueDate('');
    setIsFormOpen(false);
  };

  return (
    <div style={{ marginBottom: '24px' }}>
      <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: '16px' }}>
        <div>
          <h1 style={{ fontSize: '22px', fontWeight: 'bold', color: '#1a202c', marginBottom: '4px' }}>
            社内タスク管理
          </h1>
          <p style={{ fontSize: '13px', color: '#718096' }}>
            未着手: {stats.todo} ／ 進行中: {stats.inProgress} ／ 完了: {stats.done}
          </p>
        </div>
        <button
          onClick={() => setIsFormOpen(!isFormOpen)}
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
          {isFormOpen ? '✕ 閉じる' : '＋ タスク追加'}
        </button>
      </div>
      {isFormOpen && (
        <form
          onSubmit={handleSubmit}
          style={{
            border: '1px solid #e2e8f0',
            borderRadius: '8px',
            padding: '20px',
            backgroundColor: '#f7fafc',
          }}
        >
          <div style={{ display: 'grid', gridTemplateColumns: '1fr 1fr', gap: '12px' }}>
            <div>
              <label htmlFor="header-title" style={{ display: 'block', fontSize: '13px', marginBottom: '4px', color: '#4a5568' }}>
                タイトル <span style={{ color: '#fc8181' }}>*</span>
              </label>
              <input
                id="header-title"
                type="text"
                value={title}
                onChange={(e) => setTitle(e.target.value)}
                placeholder="タスクタイトルを入力"
                required
                style={{ width: '100%', padding: '8px', border: '1px solid #e2e8f0', borderRadius: '4px', fontSize: '14px', boxSizing: 'border-box' }}
              />
            </div>
            <div>
              <label htmlFor="header-assignee" style={{ display: 'block', fontSize: '13px', marginBottom: '4px', color: '#4a5568' }}>
                担当者 <span style={{ color: '#fc8181' }}>*</span>
              </label>
              <input
                id="header-assignee"
                type="text"
                value={assignee}
                onChange={(e) => setAssignee(e.target.value)}
                placeholder="担当者名を入力"
                required
                style={{ width: '100%', padding: '8px', border: '1px solid #e2e8f0', borderRadius: '4px', fontSize: '14px', boxSizing: 'border-box' }}
              />
            </div>
            <div>
              <label htmlFor="header-priority" style={{ display: 'block', fontSize: '13px', marginBottom: '4px', color: '#4a5568' }}>
                優先度
              </label>
              <select
                id="header-priority"
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
              <label htmlFor="header-due" style={{ display: 'block', fontSize: '13px', marginBottom: '4px', color: '#4a5568' }}>
                期限 <span style={{ color: '#fc8181' }}>*</span>
              </label>
              <input
                id="header-due"
                type="date"
                value={dueDate}
                onChange={(e) => setDueDate(e.target.value)}
                required
                style={{ width: '100%', padding: '8px', border: '1px solid #e2e8f0', borderRadius: '4px', fontSize: '14px', boxSizing: 'border-box' }}
              />
            </div>
            <div style={{ gridColumn: '1 / -1' }}>
              <label htmlFor="header-desc" style={{ display: 'block', fontSize: '13px', marginBottom: '4px', color: '#4a5568' }}>
                説明
              </label>
              <textarea
                id="header-desc"
                value={description}
                onChange={(e) => setDescription(e.target.value)}
                placeholder="タスクの詳細を入力"
                rows={2}
                style={{ width: '100%', padding: '8px', border: '1px solid #e2e8f0', borderRadius: '4px', fontSize: '14px', boxSizing: 'border-box', resize: 'vertical' }}
              />
            </div>
          </div>
          <div style={{ display: 'flex', gap: '8px', marginTop: '12px' }}>
            <button
              type="submit"
              style={{ padding: '8px 20px', backgroundColor: '#4299e1', color: '#ffffff', border: 'none', borderRadius: '4px', fontSize: '14px', cursor: 'pointer' }}
            >
              追加する
            </button>
            <button
              type="button"
              onClick={() => setIsFormOpen(false)}
              style={{ padding: '8px 20px', backgroundColor: '#e2e8f0', color: '#4a5568', border: 'none', borderRadius: '4px', fontSize: '14px', cursor: 'pointer' }}
            >
              キャンセル
            </button>
          </div>
        </form>
      )}
    </div>
  );
}

export default TaskHeader;
