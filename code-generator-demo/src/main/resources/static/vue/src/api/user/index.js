import request from '@/utils/request'

export function fetchUserList(query) {
  return request({
    url: '/mm/user',
    method: 'GET',
    params: query
  })
}

export function fetchUser(id) {
  return request({
    url: '/mm/user/' + id,
    method: 'GET'
})
}

export function createUser(formData) {
  return request({
    url: '/mm/user',
    method: 'POST',
    data: formData
  })
}

export function updateUser(formData) {
  return request({
    url: '/mm/user/' + formData.id,
    method: 'PUT',
    data: formData
  })
}

export function deleteUser(ids) {
  return request({
    url: '/mm/user/' + ids,
    method: 'DELETE'
  })
}
