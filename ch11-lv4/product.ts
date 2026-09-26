export type Product = {
  id: number;
  name: string;
  price: number;
  stock: number;
  category: string;
  description: string;
  imageUrl: string;
  isActive: boolean;
  createdAt: string;
};

export type ProductSearchParams = {
  keyword?: string;
  category?: string;
  page?: number;
  perPage?: number;
};

export type ProductSearchResult = {
  products: Product[];
  totalCount: number;
  currentPage: number;
  totalPages: number;
};
