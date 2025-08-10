export interface ApiResponse<T> {
    timestamp: string;
    statusCode: number;
    path: string;
    success: boolean;
    message: string;
    data: T;
}