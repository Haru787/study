export const initialState = {
  tasks: [
    {
      id: 1,
      title: '要件定義書の作成',
      description: 'プロジェクトの要件定義書を作成し、レビューに回す',
      status: '未着手',
      assignee: '田中 太郎',
      assigneeId: 101,
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
      assigneeId: 102,
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
      assigneeId: 101,
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
      assigneeId: 103,
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
      assigneeId: 104,
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
      assigneeId: 101,
      priority: '高',
      dueDate: '2026-05-28',
      createdAt: '2026-05-06',
    },
    {
      id: 7,
      title: '結合テスト実施',
      description: '各モジュール間の結合テストを実施し、結果を報告する',
      status: '未着手',
      assignee: '鈴木 花子',
      assigneeId: 102,
      priority: '中',
      dueDate: '2026-06-15',
      createdAt: '2026-05-07',
    },
    {
      id: 8,
      title: 'リリースノート作成',
      description: 'バージョン2.0のリリースノートを作成する',
      status: '未着手',
      assignee: '山田 美咲',
      assigneeId: 103,
      priority: '低',
      dueDate: '2026-06-20',
      createdAt: '2026-05-08',
    },
  ],
  nextId: 9,
};

export function taskReducer(state, action) {
  switch (action.type) {
    case 'ADD_TASK':
      return {
        ...state,
        tasks: [
          ...state.tasks,
          {
            ...action.payload,
            id: state.nextId,
            createdAt: new Date().toISOString().slice(0, 10),
          },
        ],
        nextId: state.nextId + 1,
      };
    case 'UPDATE_STATUS':
      return {
        ...state,
        tasks: state.tasks.map((task) =>
          task.id === action.payload.taskId
            ? { ...task, status: action.payload.newStatus }
            : task
        ),
      };
    case 'DELETE_TASK':
      return {
        ...state,
        tasks: state.tasks.filter((task) => task.id !== action.payload.taskId),
      };
    default:
      return state;
  }
}
