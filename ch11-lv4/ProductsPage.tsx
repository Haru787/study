import type { Metadata } from 'next';
import { searchProducts } from '@/lib/productApi';
import SearchBar from './components/SearchBar';
import Pagination from './components/Pagination';
import ProductList from './components/ProductList';

export const metadata: Metadata = {
  title: '商品一覧 | ECサイト管理画面',
};

type Props = {
  searchParams: { keyword?: string; category?: string; page?: string };
};

export default async function ProductsPage({ searchParams }: Props) {
  const page = Number(searchParams.page ?? 1);
  const result = await searchProducts({
    keyword: searchParams.keyword,
    category: searchParams.category,
    page,
    perPage: 12,
  });

  return (
    <main className="max-w-7xl mx-auto px-4 py-8">
      <h1 className="text-3xl font-bold text-gray-800 mb-6">商品一覧</h1>
      <SearchBar />
      <p className="text-sm text-gray-500 mb-4">{result.totalCount}件の商品</p>
      <ProductList products={result.products} />
      <Pagination currentPage={result.currentPage} totalPages={result.totalPages} />
    </main>
  );
}
