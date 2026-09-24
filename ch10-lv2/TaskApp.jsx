import React, { useState } from 'react';
import TaskBoard from './TaskBoard';
import TaskForm from './TaskForm';
import StatusBadge from './StatusBadge';

const initialTasks = [
  {
    id: 1,
    title: '要件定義書の作成',
    description: 'プロジェクトの要件定義書を作成し、レビューに回す',
    status: '未着手',
    assignee: '田中 太郎',
    priority: '高',
    dueDate: '2026-05-30',
    createdAt: '2026-05-01',
  },
  {
    id: 2,
    title: 'UIデザインのレビュー',
    description: 'デザインチームが作成したUIモックをレビューする',
    status: '進行中',
    assignee: '鈴木 花子',
    priority: '中',
    dueDate: '2026-05-25',
    createdAt: '2026-05-02',
  },
  {
    id: 3,
    title: 'APIエンドポイント実装',
    description: 'バックエンドのREST APIを実装する',
    status: '未着手',
    assignee: '佐藤 次郎',
    priority: '高',
    dueDate: '2026-06-05',
    createdAt: '2026-05-03',
  },
  {
    id: 4,
    title: '単体テスト作成',
    description: '各モジュールの単体テストを作成する',
    status: '完了',
    assignee: '山田 美咲',
    priority: '低',
    dueDate: '2026-05-20',
    createdAt: '2026-05-04',
  },
  {
    id: 5,
    title: 'デプロイ手順書の整備',
    description: '本番環境へのデプロイ手順を文書化する',
    status: '進行中',
    assignee: '伊藤 健一',
    priority: '中',
    dueDate: '2026-06-10',
    createdAt: '2026-05-05',
  },
];

function TaskApp() {
  const [tasks, setTasks] = useState(initialTasks);
  const [nextId, setNextId] = useState(6);

  const handleAddTask = (newTask) => {
    setTasks([...tasks, { ...newTask, id: nextId, createdAt: new Date().toISOString().slice(0, 10) }]);
    setNextId(nextId + 1);
  };

  const handleUpdateStatus = (taskId, newStatus) => {
    setTasks(tasks.map((task) =>
      task.id === taskId ? { ...task, status: newStatus } : task
    ));
  };

  const handleDeleteTask = (taskId) => {
    setTasks(tasks.filter((task) => task.id !== taskId));
  };

  return (
    <div style={{ maxWidth: '960px', margin: '0 auto', padding: '24px', fontFamily: 'sans-serif' }}>
      <div style={{ marginBottom: '24px' }}>
        <h1 style={{ fontSize: '24px', fontWeight: 'bold', color: '#1a202c', marginBottom: '4px' }}>
          社内タスク管理
        </h1>
        <p style={{ fontSize: '14px', color: '#718096' }}>
          チームのタスクを一元管理します
        </p>
      </div>
      <div style={{ display: 'flex', gap: '8px', marginBottom: '16px' }}>
        {['未着手', '進行中', '完了'].map((status) => (
          <StatusBadge key={status} status={status} count={tasks.filter((t) => t.status === status).length} />
        ))}
      </div>
      <TaskForm onAddTask={handleAddTask} />
      <TaskBoard
        tasks={tasks}
        onUpdateStatus={handleUpdateStatus}
        onDeleteTask={handleDeleteTask}
      />
    </div>
  );
}

export default TaskApp;
