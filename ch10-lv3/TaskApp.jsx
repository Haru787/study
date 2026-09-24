import React, { useState } from 'react';
import TaskList from './TaskList';
import TaskHeader from './TaskHeader';
import taskUtils from './taskUtils';

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
  {
    id: 6,
    title: 'コードレビュー対応',
    description: 'レビュー指摘事項を修正してプッシュする',
    status: '未着手',
    assignee: '田中 太郎',
    priority: '高',
    dueDate: '2026-05-28',
    createdAt: '2026-05-06',
  },
];

function TaskApp() {
  const [tasks, setTasks] = useState(initialTasks);

  const handleUpdateStatus = (taskId, newStatus) => {
    setTasks((prevTasks) =>
      prevTasks.map((task) =>
        task.id === taskId ? { ...task, status: newStatus } : task
      )
    );
  };

  const handleDeleteTask = (taskId) => {
    setTasks(tasks.filter((task) => task.id !== taskId));
  };

  const handleAddTask = (newTask) => {
    const maxId = tasks.reduce((max, t) => (t.id > max ? t.id : max), 0);
    setTasks([
      ...tasks,
      {
        ...newTask,
        id: maxId + 1,
        createdAt: new Date().toISOString().slice(0, 10),
      },
    ]);
  };

  const stats = taskUtils.calcStats(tasks);

  return (
    <div style={{ maxWidth: '960px', margin: '0 auto', padding: '24px', fontFamily: 'sans-serif' }}>
      <TaskHeader stats={stats} onAddTask={handleAddTask} />
      <TaskList
        tasks={tasks}
        onUpdateStatus={handleUpdateStatus}
        onDeleteTask={handleDeleteTask}
      />
    </div>
  );
}

export default TaskApp;
