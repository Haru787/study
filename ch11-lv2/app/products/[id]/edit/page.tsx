import type { Metadata } from 'next';
import { fetchProduct } from '@/lib/api';
import EditFormClient from '../components/EditFormClient';

export const metadata: Metadata = {
  title: '商品編集 | ECサイト管理画面',
};

type Props = {
  params: { id: string };
};

export default async function EditPage({ params }: Props) {
  const product = await fetchProduct(Number(params.id));

  return (
    <main className="max-w-2xl mx-auto px-4 py-8">
      <h1 className="text-3xl font-bold text-gray-800 mb-6">商品編集</h1>
      <EditFormClient product={product} />
    </main>
  );
}
