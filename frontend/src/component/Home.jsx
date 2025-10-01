import React, { useEffect, useState, useCallback } from "react";
import axios from "axios";

const API_BASE = "http://localhost:8080";
const API = `${API_BASE}/api/todos`;
const LOGIN_PAGE = `${API_BASE}/login`;

// Create an axios instance that always sends cookies
const api = axios.create({
  baseURL: API_BASE,
  withCredentials: true, // <-- important: send JSESSIONID across ports
});

// If we ever get 401, bounce the user to the Spring login page
api.interceptors.response.use(
  (res) => res,
  (err) => {
    if (err?.response?.status === 401) {
      window.location.href = LOGIN_PAGE;
    }
    return Promise.reject(err);
  }
);

export const Home = () => {
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [todos, setTodos] = useState([]);
  const [loading, setLoading] = useState(true);
  const [creating, setCreating] = useState(false);

  const fetchAllTodos = useCallback(async () => {
    try {
      setLoading(true);
      const { data } = await api.get(`${API}`); // baseURL + path
      setTodos(data);
    } catch (error) {
      console.log("fetchAllTodos error:", error);
    } finally {
      setLoading(false);
    }
  }, []);

  useEffect(() => {
    fetchAllTodos();
  }, [fetchAllTodos]);

  const createTodo = async () => {
    if (!title.trim()) return;
    const body = { title: title.trim(), description: description.trim() };

    try {
      setCreating(true);
      const { data } = await api.post(`${API}`, body);
      // append the new one and clear inputs
      setTodos((prev) => [...prev, data]);
      setTitle("");
      setDescription("");
    } catch (error) {
      console.log("createTodo error:", error);
    } finally {
      setCreating(false);
    }
  };

  const deleteTodo = async (id) => {
    try {
      await api.delete(`/api/todos/${id}`);
      setTodos((prev) => prev.filter((t) => t.id !== id));
    } catch (error) {
      console.log("deleteTodo error:", error);
    }
  };

  return (
    <div className="w-[50vw] h-[80vh] bg-white rounded-xl">
      <div className="bg-[#758AA2] p-5 flex gap-5 justify-center rounded-t-xl">
        <input
          className="p-2 rounded-md w-1/2 outline-none px-5 text-black"
          placeholder="Title"
          type="text"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
        />
        <input
          className="p-2 rounded-md w-1/2 outline-none px-5 text-black"
          placeholder="Description (optional)"
          type="text"
          value={description}
          onChange={(e) => setDescription(e.target.value)}
        />
        <button
          onClick={createTodo}
          disabled={!title.trim() || creating}
          className={`py-2 px-5 rounded-md bg-[#2B2B52] ${
            (!title.trim() || creating) ? "opacity-60 cursor-not-allowed" : ""
          }`}
        >
          {creating ? "Adding..." : "Add"}
        </button>
      </div>

      <h1 className="text-black text-center pt-10 font-bold">My Todos</h1>

      <div className="p-5 space-y-2 overflow-y-auto h-[60vh]">
        {loading ? (
          <p className="text-gray-700">Loading…</p>
        ) : todos.length === 0 ? (
          <p className="text-gray-700">No todos yet. Add your first one!</p>
        ) : (
          todos.map((item, index) => (
            <div
              key={item.id ?? `${item.title}-${index}`}
              className="bg-[#99AAAB] p-3 rounded-md flex items-center justify-between"
            >
              <div>
                <p className="text-gray-900 text-sm">
                  {index + 1}. {item.title}
                </p>
                {item?.description && (
                  <p className="text-xs text-gray-700 mt-1">
                    {item.description}
                  </p>
                )}
              </div>
              <div className="flex space-x-4">
                <button
                  onClick={() => deleteTodo(item.id)}
                  className="text-red-600 hover:text-white focus:outline-none rounded-full hover:bg-red-600 p-2"
                  aria-label="Delete"
                  title="Delete"
                >
                  <svg
                    className="h-6 w-6"
                    fill="none"
                    viewBox="0 0 24 24"
                    stroke="currentColor"
                  >
                    <path
                      strokeLinecap="round"
                      strokeLinejoin="round"
                      strokeWidth={2}
                      d="M6 18L18 6M6 6l12 12"
                    />
                  </svg>
                </button>
              </div>
            </div>
          ))
        )}
      </div>
    </div>
  );
};
