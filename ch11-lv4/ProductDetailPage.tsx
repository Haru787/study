import type { Metadata } from 'next';
import { fetchProduct } from '@/lib/productApi';
import ProductDetail from './components/ProductDetail';
import DeleteButton from './DeleteButton';

type Props = {
  params: Promise<{ id: string }>;
};

export async function generateMetadata({ params }: Props): Promise<Metadata> {
  const { id } = await params;
  const product = await fetchProduct(Number(id));
  return { title: `${product.name} | ECサイト管理画面` };
}

export default async function ProductDetailPage({ params }: Props) {
  const { id } = await params;
  const product = await fetchProduct(Number(id));

  return (
    <main className="max-w-5xl mx-auto px-4 py-8">
      <nav className="text-sm text-gray-500 mb-6">
        <a href="/products" className="hover:underline">商品一覧</a>
        <span className="mx-2">/</span>
        <span>{product.name}</span>
      </nav>
      <ProductDetail product={product} />
      <div className="flex gap-3 mt-6">
        <a
          href={`/products/${product.id}/edit`}
          className="px-5 py-2 bg-indigo-600 text-white rounded-md hover:bg-indigo-700"
        >
          編集する
        </a>
        <DeleteButton productId={product.id} />
      </div>
    </main>
  );
}
