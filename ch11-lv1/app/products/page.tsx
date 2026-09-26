import type { Metadata } from 'next';
import type { Product } from '@/types/product';
import ProductList from './components/ProductList';
import ProductFilter from './components/ProductFilter';

export const metadata: Metadata = {
  title: '商品一覧 | ECサイト管理画面',
};

async function fetchProducts(): Promise<Product[]> {
  const res = await fetch(`${process.env.API_BASE_URL}/api/products`, {
    cache: 'no-store',
  });
  if (!res.ok) {
    throw new Error('商品一覧の取得に失敗しました');
  }
  return res.json();
}

export default async function ProductsPage() {
  const products = await fetchProducts();

  return (
    <main className="max-w-7xl mx-auto px-4 py-8">
      <h1 className="text-3xl font-bold text-gray-800 mb-6">商品一覧</h1>
      <ProductFilter />
      <ProductList products={products} />
    </main>
  );
}
