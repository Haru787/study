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

export type ProductCategory = '電化製品' | '衣類' | '食品' | '家具' | 'その他';
