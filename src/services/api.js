const API_BASE_URL = 'http://localhost:8081/api';

class ApiService {
  async request(endpoint, options = {}) {
    const url = `${API_BASE_URL}${endpoint}`;
    const config = {
      headers: {
        'Content-Type': 'application/json',
        ...options.headers,
      },
      ...options,
    };

    try {
      const response = await fetch(url, config);

      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }

      const data = await response.text();

      try {
        return JSON.parse(data);
      } catch {
        return data;
      }
    } catch (error) {
      console.error('API request failed:', error);
      throw error;
    }
  }

  async signUp(userData) {
    return this.request('/users/signup', {
      method: 'POST',
      body: JSON.stringify(userData),
    });
  }

  async signIn(email, password) {
    return this.request('/users/signin', {
      method: 'POST',
      body: JSON.stringify({ email, password }),
    });
  }

  async forgotPassword(email) {
    return this.request(`/users/forgotpassword/${email}`, {
      method: 'GET',
    });
  }

  async getFullName(csrid) {
    return this.request('/users/fullname', {
      method: 'POST',
      body: JSON.stringify({ csrid }),
    });
  }

  async getUserProfile(email) {
    return this.request(`/users/profile/${email}`, {
      method: 'GET',
    });
  }

  async getAllFoodItems() {
    return this.request('/fooditems', {
      method: 'GET',
    });
  }

  async getFoodItemById(id) {
    return this.request(`/fooditems/${id}`, {
      method: 'GET',
    });
  }

  async getFoodItemsByCategory(category) {
    return this.request(`/fooditems/category/${category}`, {
      method: 'GET',
    });
  }

  async getFoodItemsByCuisine(cuisine) {
    return this.request(`/fooditems/cuisine/${cuisine}`, {
      method: 'GET',
    });
  }

  async searchFoodItems(keyword) {
    return this.request(`/fooditems/search?keyword=${encodeURIComponent(keyword)}`, {
      method: 'GET',
    });
  }

  async getAllCategories() {
    return this.request('/fooditems/categories', {
      method: 'GET',
    });
  }

  async getAllCuisines() {
    return this.request('/fooditems/cuisines', {
      method: 'GET',
    });
  }

  async getTopRatedFoodItems() {
    return this.request('/fooditems/top-rated', {
      method: 'GET',
    });
  }

  async createFoodItem(foodItem) {
    return this.request('/fooditems', {
      method: 'POST',
      body: JSON.stringify(foodItem),
    });
  }

  async updateFoodItem(id, foodItem) {
    return this.request(`/fooditems/${id}`, {
      method: 'PUT',
      body: JSON.stringify(foodItem),
    });
  }

  async deleteFoodItem(id) {
    return this.request(`/fooditems/${id}`, {
      method: 'DELETE',
    });
  }
}

export default new ApiService();
