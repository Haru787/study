const taskUtils = {
  calcStats(tasks) {
    return {
      todo: tasks.filter((t) => t.status === '未着手').length,
      inProgress: tasks.filter((t) => t.status === '進行中').length,
      done: tasks.filter((t) => t.status === '完了').length,
      total: tasks.length,
    };
  },

  sortByPriority(tasks) {
    const order = { 高: 0, 中: 1, 低: 2 };
    return [...tasks].sort((a, b) => (order[a.priority] ?? 9) - (order[b.priority] ?? 9));
  },

  sortByDueDate(tasks) {
    return [...tasks].sort((a, b) => new Date(a.dueDate) - new Date(b.dueDate));
  },

  filterByStatus(tasks, status) {
    if (status === 'すべて') return tasks;
    return tasks.filter((t) => t.status === status);
  },

  filterByAssignee(tasks, assignee) {
    if (!assignee) return tasks;
    return tasks.filter((t) => t.assignee.includes(assignee));
  },

  isOverdue(task) {
    if (task.status === '完了') return false;
    return new Date(task.dueDate) < new Date();
  },
};

export default taskUtils;
