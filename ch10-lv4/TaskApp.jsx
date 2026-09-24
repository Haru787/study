import React, { useState, useEffect } from 'react';
import TaskList from './TaskList';
import SearchBar from './SearchBar';
import FilterPanel from './FilterPanel';
import taskApi from './taskApi';

function TaskApp() {
  const [tasks, setTasks] = useState([]);
  const [filteredTasks, setFilteredTasks] = useState([]);
  const [filterStatus, setFilterStatus] = useState('すべて');
  const [searchKeyword, setSearchKeyword] = useState('');
  const [isLoading, setIsLoading] = useState(false);
  const [nextId, setNextId] = useState(8);

  useEffect(() => {
    let isCancelled = false;

    const fetchTasks = async () => {
      setIsLoading(true);
      const data = await taskApi.fetchTasksByStatus(filterStatus);
      if (!isCancelled) {
        setTasks(data);
        setIsLoading(false);
      }
    };

    fetchTasks();

    return () => {
      isCancelled = true;
    };
  }, [filterStatus]);

  useEffect(() => {
    let result = tasks;
    if (searchKeyword.trim()) {
      result = result.filter(
        (task) =>
          task.title.includes(searchKeyword) ||
          task.assignee.includes(searchKeyword) ||
          task.description.includes(searchKeyword)
      );
    }
    setFilteredTasks(result);
  }, [tasks, searchKeyword]);

  const handleFilterChange = (newStatus) => {
    setFilterStatus(newStatus);
  };

  const handleAddTask = (newTask) => {
    setTasks([
      ...tasks,
      {
        ...newTask,
        id: nextId,
        createdAt: new Date().toISOString().slice(0, 10),
      },
    ]);
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
        <h1 style={{ fontSize: '22px', fontWeight: 'bold', color: '#1a202c', marginBottom: '4px' }}>
          社内タスク管理
        </h1>
        <p style={{ fontSize: '13px', color: '#718096' }}>
          全 {tasks.length} 件（表示中: {filteredTasks.length} 件）
        </p>
      </div>
      <div style={{ display: 'flex', gap: '12px', marginBottom: '16px', flexWrap: 'wrap' }}>
        <SearchBar keyword={searchKeyword} onKeywordChange={setSearchKeyword} />
        <FilterPanel filterStatus={filterStatus} onFilterChange={handleFilterChange} />
      </div>
      {isLoading ? (
        <div style={{ textAlign: 'center', padding: '48px', color: '#a0aec0' }}>
          <p>読み込み中...</p>
        </div>
      ) : (
        <TaskList
          tasks={filteredTasks}
          onUpdateStatus={handleUpdateStatus}
          onDeleteTask={handleDeleteTask}
          onAddTask={handleAddTask}
        />
      )}
    </div>
  );
}

export default TaskApp;
