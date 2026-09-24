import React from 'react';
import TaskCard from './TaskCard';

function TaskList({ tasks, onUpdateStatus, onDeleteTask, onAddTask }) {
  const [isFormOpen, setIsFormOpen] = React.useState(false);
  const [newTitle, setNewTitle] = React.useState('');
  const [newAssignee, setNewAssignee] = React.useState('');
  const [newPriority, setNewPriority] = React.useState('中');
  const [newDueDate, setNewDueDate] = React.useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!newTitle.trim() || !newAssignee.trim() || !newDueDate) return;
    onAddTask({
      title: newTitle.trim(),
      description: '',
      assignee: newAssignee.trim(),
      priority: newPriority,
      dueDate: newDueDate,
      status: '未着手',
    });
    setNewTitle('');
    setNewAssignee('');
    setNewPriority('中');
    setNewDueDate('');
    setIsFormOpen(false);
  };

  return (
    <div>
      <div style={{ marginBottom: '16px' }}>
        <button
          onClick={() => setIsFormOpen(!isFormOpen)}
          style={{
            padding: '8px 18px',
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
            padding: '16px',
            marginBottom: '16px',
            backgroundColor: '#f7fafc',
            display: 'grid',
            gridTemplateColumns: '1fr 1fr',
            gap: '10px',
          }}
        >
          <input
            type="text"
            value={newTitle}
            onChange={(e) => setNewTitle(e.target.value)}
            placeholder="タスクタイトル *"
            required
            style={{ padding: '8px', border: '1px solid #e2e8f0', borderRadius: '4px', fontSize: '14px' }}
          />
          <input
            type="text"
            value={newAssignee}
            onChange={(e) => setNewAssignee(e.target.value)}
            placeholder="担当者名 *"
            required
            style={{ padding: '8px', border: '1px solid #e2e8f0', borderRadius: '4px', fontSize: '14px' }}
          />
          <select
            value={newPriority}
            onChange={(e) => setNewPriority(e.target.value)}
            style={{ padding: '8px', border: '1px solid #e2e8f0', borderRadius: '4px', fontSize: '14px' }}
          >
            <option value="高">高優先</option>
            <option value="中">中優先</option>
            <option value="低">低優先</option>
          </select>
          <input
            type="date"
            value={newDueDate}
            onChange={(e) => setNewDueDate(e.target.value)}
            required
            style={{ padding: '8px', border: '1px solid #e2e8f0', borderRadius: '4px', fontSize: '14px' }}
          />
          <div style={{ gridColumn: '1 / -1', display: 'flex', gap: '8px' }}>
            <button
              type="submit"
              style={{ padding: '8px 18px', backgroundColor: '#4299e1', color: '#ffffff', border: 'none', borderRadius: '4px', fontSize: '14px', cursor: 'pointer' }}
            >
              追加
            </button>
            <button
              type="button"
              onClick={() => setIsFormOpen(false)}
              style={{ padding: '8px 18px', backgroundColor: '#e2e8f0', color: '#4a5568', border: 'none', borderRadius: '4px', fontSize: '14px', cursor: 'pointer' }}
            >
              キャンセル
            </button>
          </div>
        </form>
      )}
      {tasks.length === 0 ? (
        <div style={{ textAlign: 'center', padding: '48px', color: '#a0aec0' }}>
          <p>該当するタスクがありません</p>
        </div>
      ) : (
        <div style={{ display: 'flex', flexDirection: 'column', gap: '12px' }}>
          {tasks.map((task) => (
            <TaskCard
              key={task.id}
              task={task}
              onUpdateStatus={onUpdateStatus}
              onDeleteTask={onDeleteTask}
            />
          ))}
        </div>
      )}
    </div>
  );
}

export default TaskList;
