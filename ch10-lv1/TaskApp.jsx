import React, { useState } from 'react';
import TaskList from './TaskList';
import TaskForm from './TaskForm';
import TaskFilter from './TaskFilter';

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
];

function TaskApp() {
  const [tasks, setTasks] = useState(initialTasks);
  const [filterStatus, setFilterStatus] = useState('すべて');
  const [nextId, setNextId] = useState(5);

  const handleAddTask = (newTask) => {
    setTasks([...tasks, { ...newTask, id: nextId, createdAt: new Date().toISOString().slice(0, 10) }]);
    setNextId(nextId + 1);
  };

  const handleCompleteTask = (taskId) => {
    setTasks(tasks.map((task) =>
      task.id === taskId ? { ...task, status: '完了' } : task
    ));
  };

  const handleDeleteTask = (taskId) => {
    setTasks(tasks.filter((task) => task.id !== taskId));
  };

  const filteredTasks = filterStatus === 'すべて'
    ? tasks
    : tasks.filter((task) => task.status === filterStatus);

  return (
    <div style={{ maxWidth: '900px', margin: '0 auto', padding: '24px', fontFamily: 'sans-serif' }}>
      <h1 style={{ fontSize: '24px', fontWeight: 'bold', marginBottom: '24px', color: '#1a202c' }}>
        社内タスク管理
      </h1>
      <TaskForm onAddTask={handleAddTask} />
      <TaskFilter filterStatus={filterStatus} onFilterChange={setFilterStatus} />
      <TaskList
        tasks={filteredTasks}
        onCompleteTask={handleCompleteTask}
        onDeleteTask={handleDeleteTask}
      />
    </div>
  );
}

export default TaskApp;
